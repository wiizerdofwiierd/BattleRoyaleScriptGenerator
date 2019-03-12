package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.menubar;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.console.WindowConsole;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.*;

public class MenuHelp extends JMenu{
	
	public MenuHelp(){
		super("Help");
		
		this.setMnemonic('H');

		WindowConsole consoleWindow = Main.getConsoleWindow();
		if(consoleWindow != null){
			JMenuItem consoleButton = new JMenuItem("Console");
			consoleButton.addActionListener(actionEvent -> consoleWindow.setVisible(true));
			this.add(consoleButton);

			this.addSeparator();
		}

		for(Author a : Author.values()){
			JMenuItem versionButton = new JMenuItem(String.format("%s by %s", a.getContribution(), a.getName()));
			
			String homepage = a.getHomepage();
			if(homepage != null){
				versionButton.addActionListener(actionEvent -> Util.openInBrowser(homepage));
			}
			
			this.add(versionButton);
		}
	}
	
	private enum Author{
		AUTHOR_PROGRAM("Battle Royale Script Generator v" + Main.VERSION, "wiizerdofwiierd", "https://github.com/wiizerdofwiierd/BattleRoyaleScriptGenerator"),
		AUTHOR_SCRIPT("Script template", "Lifeinsteps"),
		AUTHOR_SIMULATOR("Hunger Games Simulator", "Brant Steele", "http://www.brantsteele.net/");
		
		private String contribution;
		private String name;
		private String homepage;
		
		Author(String contribution, String name, String homepage){
			this.contribution = contribution;
			this.name = name;
			this.homepage = homepage;
		}
		
		Author(String contribution, String name){
			this(contribution, name, null);
		}
		
		public String getContribution(){
			return this.contribution;
		}
		
		public String getName(){
			return this.name;
		}
		
		public String getHomepage(){
			return this.homepage;
		}
	}
}
