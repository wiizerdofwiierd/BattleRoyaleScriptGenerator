package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.UpdatableBotComponent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.add.PanelMemberAdd;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.PanelMemberManage;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.PanelMemberRemove;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;
import java.awt.*;

public class WindowUserSelect extends JFrame implements UpdatableBotComponent{

	private IDiscordClient client;
	private IGuild guild;
	
	private PanelMemberAdd leftPanel;
	private PanelMemberManage centerPanel;
	private PanelMemberRemove rightPanel;
	
	private Settings settings;
	private Member[] selectedMembers = new Member[0];
	
	public WindowUserSelect(String title, IDiscordClient client, IGuild guild){
		super(title);

		this.client = client;
		this.guild = guild;
		this.settings = Main.getSettingsHandler().getSettingsForGuild(guild);
		
		MenuBarUserSelect menuBar = new MenuBarUserSelect(this);
		this.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Select Users"));
		
		panel.setLayout(new GridLayout(1, 3));
		
		this.settings.getMembers().repopulate(this.guild);
		
		this.leftPanel = new PanelMemberAdd(this, "Members on " + this.guild.getName());
		panel.add(leftPanel);

		this.centerPanel = new PanelMemberManage(this);
		panel.add(centerPanel);

		this.rightPanel = new PanelMemberRemove(this, "Members Participating");
		panel.add(rightPanel);
		
		this.add(panel);
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.5F, 0.5F));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.update();
	}
	
	public IDiscordClient getClient(){
		return this.client;
	}
	
	public IGuild getGuild(){
		return this.guild;
	}
	
	public PanelMemberAdd getLeftPanel(){
		return this.leftPanel;
	}
	
	public PanelMemberManage getCenterPanel(){
		return this.centerPanel;
	}
	
	public PanelMemberRemove getRightPanel(){
		return this.rightPanel;
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
		this.rightPanel.getGeneratePanel().getButton().update();
	}
	
	@Override
	public void update(){
		this.leftPanel.update();
		this.centerPanel.update();
		this.rightPanel.update();
	}
}
