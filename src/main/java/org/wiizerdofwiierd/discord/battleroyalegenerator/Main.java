package org.wiizerdofwiierd.discord.battleroyalegenerator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.LoggerFactory;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedEventsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.console.WindowConsole;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild.WindowGuildSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.setup.WindowTokenInput;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public final class Main{
	
	public static final String VERSION = "2.0";
	
	public static final File TOKEN_FILE = new File(System.getProperty("user.dir") + File.separator + "token.txt");
	
	private static JDA client = null;
	
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

		SavedSettingsHandler.getInstance().load();
		SavedEventsHandler.getInstance().load();
		
		initLogin();
		
		client.addEventListener(new ReadyListener());

		SwingUtilities.invokeLater(() -> {
			guildSelectionWindow = new WindowGuildSelect(client);
			guildSelectionWindow.setVisible(true);
		});

//		String workingDir = System.getProperty("user.dir");
		
		/* Load events from other json files
		EventLoader loader = new EventLoader();
		loader.load(new File(workingDir + File.separator + "bloodbath.json"), EventContext.BLOODBATH);
		loader.load(new File(workingDir + File.separator + "day.json"), EventContext.DAY);
		loader.load(new File(workingDir + File.separator + "night.json"), EventContext.NIGHT);
		*/
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
		catch(LoginException e){
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Failed to log in. Is your token.txt correct?", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-2);
		}
	}
	
	public static JDA login(String token) throws LoginException{
		return JDABuilder.create(token, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)).build();
	}

	public static JDA getClient(){
		return client;
	}
	
	public static WindowGuildSelect getGuildSelectionWindow(){
		return guildSelectionWindow;
	}
	
	public static WindowConsole getConsoleWindow(){
		return consoleWindow;
	}
}
