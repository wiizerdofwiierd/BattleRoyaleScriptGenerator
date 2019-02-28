package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;

import javax.swing.*;

public class ButtonMemberSwap extends JButton{

	protected PanelManageTributes tributesPanel;
	
	private boolean participation;

	public ButtonMemberSwap(PanelManageTributes tributesPanel, String title, boolean participationToSet){
		super(title);

		this.tributesPanel = tributesPanel;
		this.participation = participationToSet;

		this.addActionListener(actionEvent -> {
			System.out.println("Setting " + this.tributesPanel.getSelectedMembers().length + " members to participation = " + this.participation);
			
			for(Member m : this.tributesPanel.getSelectedMembers()){
				m.setParticipating(this.participation);
			}
			
			this.tributesPanel.updateMemberLists();
		});
		
		this.setEnabled(false);
	}
	
	public void update(){
		Member[] selected = this.tributesPanel.getSelectedMembers();
		if(selected.length == 0){
			this.setEnabled(false);
		}
		else{
			this.setEnabled(selected[0].isParticipating() != this.participation);
		}
	}
}
