package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;

public class ButtonReset extends JButton{
	
	private static final String TEXT_RESET = "Reset selected members";
	private static final String TEXT_REMOVE = "Remove custom members";
	private static final String TEXT_MIXED = "Reset selected/remove custom members";
	
	private PanelManageTributes tributesPanel;
	private PanelSettings settingsPanel;
	
	public ButtonReset(PanelManageTributes tributesPanel, PanelSettings settingsPanel){
		super("Reset selected members");
		this.tributesPanel = tributesPanel;
		this.settingsPanel = settingsPanel;
		
		this.setEnabled(false);

		this.addActionListener(actionEvent -> {
			for(Member m : this.tributesPanel.getSelectedMembers()){
				
				if(m.isCustom()){
					this.tributesPanel.getSettings().getMembers().remove(m);
				}
				else{
					IDiscordClient client = this.tributesPanel.getMainWindow().getClient();
					IGuild guild = this.tributesPanel.getMainWindow().getGuild();
					
					m.restoreInfo(client.getUserByID(m.getId()), guild);
				}
			}

			tributesPanel.updateMemberLists();
		});
	}
	
	public void update(){
		Member[] selected = this.tributesPanel.getSelectedMembers();
		
		boolean foundMember = false;
		boolean foundCustom = false;
		
		for(Member m : selected){
			if(m.isCustom())
				foundCustom = true;
			else
				foundMember = true;
		}
		
		if(foundMember && foundCustom){
			this.setText(TEXT_MIXED);
		}
		else if(foundCustom){
			this.setText(TEXT_REMOVE);
		}
		else{
			this.setText(TEXT_RESET);
		}

		this.setEnabled(foundMember || foundCustom);
	}
}
