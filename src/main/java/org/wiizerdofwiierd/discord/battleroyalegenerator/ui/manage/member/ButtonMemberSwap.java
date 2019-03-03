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
			int size = tributesPanel.getSelectedMembers().length;
			System.out.printf("Setting %d members to participation = %s%n", size, this.participation);
			
			for(Member m : this.tributesPanel.getSelectedMembers()){
				m.setParticipating(this.participation);
			}
			
			this.tributesPanel.updateMemberLists();
			
			if(participationToSet == true){
				tributesPanel.getMainWindow().setStatusBarText("Added %d members to the game", size);
			}
			else{
				tributesPanel.getMainWindow().setStatusBarText("Removed %d members from the game", size);
			}
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
