package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event.PanelManageEvents;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member.PanelManageTributes;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;

public class WindowGameManage extends JFrame{

	private IDiscordClient client;
	private IGuild guild;

	private Settings settings;
	
	private JTabbedPane tabbedPane;
	private PanelManageTributes tributesPanel;
	
	public WindowGameManage(IDiscordClient client, IGuild guild){
		super("Manage Game Session");

		this.client = client;
		this.guild = guild;
		
		this.settings = SavedSettingsHandler.getInstance().getSettingsForGuild(guild);
		this.settings.getMembers().repopulate(this.guild);
		
		//Create and set the menu bar
		MenuBarGameManage menuBar = new MenuBarGameManage(this);
		this.setJMenuBar(menuBar);
		
		//Tabbed pane containing tabs for Tributes as well as each type of Event
		this.tabbedPane = new JTabbedPane();
		this.add(tabbedPane);
		
		//Create the tributes panel
		this.tributesPanel = new PanelManageTributes(this);
		
		//Add the tributes panel under the 'Tributes' tab
		tabbedPane.addTab("Tributes", tributesPanel);
		
		//Add a tab for each Event type
		for(EventContext context : EventContext.values()){
			tabbedPane.addTab(context.getName() + " Events", new PanelManageEvents(this, context));
		}
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.5F, 0.5F));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public PanelManageTributes getTributesPanel(){
		return this.tributesPanel;
	}
	
	public IDiscordClient getClient(){
		return this.client;
	}

	public IGuild getGuild(){
		return this.guild;
	}
	
	public Settings getSettings(){
		return this.settings;
	}
	
	public MemberList getMembers(){
		return this.settings.getMembers();
	}
	
	public void setSettings(Settings settings){
		this.settings = settings;
	}
	
	public void setSelectedTab(int index){
		this.tabbedPane.setSelectedIndex(index);
	}
	
}
