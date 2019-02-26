package org.wiizerdofwiierd.discord.battleroyalegenerator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.console.WindowConsole;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild.WindowGuildSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.setup.WindowTokenInput;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main{
	
	public static final String VERSION = "1.4";
	
	public static final File TOKEN_FILE = new File(System.getProperty("user.dir") + File.separator + "token.txt");
	
	private static IDiscordClient client = null;
	
	private static WindowGuildSelect guildSelectionWindow = null;
	private static WindowConsole consoleWindow = null;

	public static void main(String[] args){
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).setLevel(Level.WARN);
		
		try{
			ToolTipManager.sharedInstance().setInitialDelay(0);
			ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("ToolTip.background", Color.WHITE);
		}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}

		consoleWindow = new WindowConsole();

		SettingsHandler.getInstance().load();
		
		initLogin();
		
		client.getDispatcher().registerListener(new EventListener());

		SwingUtilities.invokeLater(() -> {
			guildSelectionWindow = new WindowGuildSelect(client);
			guildSelectionWindow.setVisible(true);
		});
	}

	public static void initLogin(){
		String token = null;
		
		if(!TOKEN_FILE.exists()){
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(TOKEN_FILE))){
				TOKEN_FILE.createNewFile();

				WindowTokenInput tokenInput = new WindowTokenInput();
				tokenInput.setVisible(true);
				
				token = tokenInput.getToken();
				
				writer.write(token);
			}
			catch(IOException e){
				Util.printException("Failed to create file " + TOKEN_FILE.getAbsolutePath(), e);
			}
		}
		else{
			try(BufferedReader reader = new BufferedReader(new FileReader(TOKEN_FILE))){
				token = reader.readLine();
			}
			catch(IOException e){
				Util.printException("Failed to open file " + TOKEN_FILE.getAbsolutePath() + " for reading", e);
			}
		}

		try{
			client = login(token);
		}
		catch(DiscordException e){
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Failed to log in. Is your token.txt correct?", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-2);
		}
	}
	
	public static IDiscordClient login(String token){
		return new ClientBuilder().withToken(token).login();
	}

	public static IDiscordClient getClient(){
		return client;
	}
	
	public static WindowGuildSelect getGuildSelectionWindow(){
		return guildSelectionWindow;
	}
	
	public static WindowConsole getConsoleWindow(){
		return consoleWindow;
	}
}
