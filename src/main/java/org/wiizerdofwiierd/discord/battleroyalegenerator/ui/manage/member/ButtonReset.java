package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;

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
			int length = this.tributesPanel.getSelectedMembers().length;
			
			for(GameMember m : this.tributesPanel.getSelectedMembers()){
				
				if(m.isCustom()){
					this.tributesPanel.getSettings().getMembers().remove(m);
				}
				else{
					JDA client = this.tributesPanel.getMainWindow().getClient();
					Guild guild = this.tributesPanel.getMainWindow().getGuild();
					
					Member member = guild.getMember(client.getUserById(m.getId()));
					m.restoreInfo(member);
				}
			}

			tributesPanel.updateMemberLists();
			tributesPanel.getMainWindow().setStatusBarText("Reset/removed %d members(s)", length);
		});
	}
	
	public void update(){
		GameMember[] selected = this.tributesPanel.getSelectedMembers();
		
		boolean foundMember = false;
		boolean foundCustom = false;
		
		for(GameMember m : selected){
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
