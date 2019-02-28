package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;
import java.awt.*;

public class WindowGameManage extends JFrame{

	private IDiscordClient client;
	private IGuild guild;
	
	private PanelListServer listServer;
	private PanelListManage buttonPanel;
	private PanelListSelected listSelected;
	
	private Settings settings;
	private Member[] selectedMembers = new Member[0];
	
	public WindowGameManage(IDiscordClient client, IGuild guild){
		super("Manage Game Session");

		this.client = client;
		this.guild = guild;
		this.settings = SettingsHandler.getInstance().getSettingsForGuild(guild);
		
		MenuBarGameManage menuBar = new MenuBarGameManage(this);
		this.setJMenuBar(menuBar);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		this.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Select Members"));
		
		panel.setLayout(new GridLayout(1, 3));
		
		this.settings.getMembers().repopulate(this.guild);
		
		this.listServer = new PanelListServer(this);
		panel.add(listServer);

		this.buttonPanel = new PanelListManage(this);
		panel.add(buttonPanel);

		this.listSelected = new PanelListSelected(this);
		panel.add(listSelected);

		this.settings.showBots.addChangeListener(((oldValue, newValue) -> this.updateListPanels()));
		this.settings.showCustom.addChangeListener(((oldValue, newValue) -> this.updateListPanels()));
		
		tabbedPane.addTab("Tributes", panel);
		
		for(EventContext context : EventContext.values()){
			tabbedPane.addTab(context.getName() + " Events", new PanelEventList(context));
		}
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.5F, 0.5F));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.updateListPanels();
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
	
	public Member[] getSelectedMembers(){
		return this.selectedMembers;
	}
	
	public void setSettings(Settings settings){
		this.settings = settings;
	}
	
	public void setSelectedMembers(Member[] selectedMembers){
		this.selectedMembers = selectedMembers;
	}
	
	public void updateGenerateButton(){
		this.listSelected.getGeneratePanel().getButton().update();
	}
	
	public void updateListPanels(){
		this.listServer.update();
		this.listSelected.update();
	}
	
	public void updateButtonPanel(){
		this.buttonPanel.update();
	}
}
