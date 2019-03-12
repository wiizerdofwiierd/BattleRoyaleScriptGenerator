package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event.PanelManageEvents;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member.PanelManageTributes;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.*;
import java.awt.*;

public class WindowGameManage extends JFrame{

	private JDA client;
	private Guild guild;

	private Settings settings;
	
	private JTabbedPane tabbedPane;
	private PanelManageTributes tributesPanel;
	private PanelStatusBar statusBar;
	
	public WindowGameManage(JDA client, Guild guild){
		super("Manage Game Session");

		this.client = client;
		this.guild = guild;
		
		this.settings = SavedSettingsHandler.getInstance().getSettingsForGuild(guild);
		this.settings.getMembers().repopulate(this.guild);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill    = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		//Create and set the menu bar
		MenuBarGameManage menuBar = new MenuBarGameManage(this);
		this.setJMenuBar(menuBar);
		
		//Tabbed pane containing tabs for Tributes as well as each type of Event
		this.tabbedPane = new JTabbedPane();
		
		c.gridx = 0;
		c.gridy = 0;
		
		this.add(tabbedPane, c);
		
		//Create the tributes panel
		this.tributesPanel = new PanelManageTributes(this);
		
		//Add the tributes panel under the 'Tributes' tab
		tabbedPane.addTab("Tributes", tributesPanel);
		
		//Add a tab for each Event type
		for(EventContext context : EventContext.values()){
			tabbedPane.addTab(context.getName() + " Events", new PanelManageEvents(this, context));
			
			tabbedPane.addChangeListener(e -> {
				Component component = tabbedPane.getSelectedComponent();
				if(component instanceof PanelManageEvents){
					
					//Update the list on the tab we're switching to
					((PanelManageEvents) component).getEventListPanel().getEventList().update();
				}
			});
		}
		
		c.gridx   = 0;
		c.gridy   = 1;
		c.weighty = 0.0;
		
		this.statusBar = new PanelStatusBar("Loaded %d members for guild %s", this.settings.getMembers().size(), guild.getName());
		this.add(statusBar, c);
		
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.5F, 0.5F));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public PanelManageTributes getTributesPanel(){
		return this.tributesPanel;
	}
	
	public JDA getClient(){
		return this.client;
	}

	public Guild getGuild(){
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
	
	public void setStatusBarText(String text, Object... values){
		this.statusBar.setStatus(text, values);
	}
}
