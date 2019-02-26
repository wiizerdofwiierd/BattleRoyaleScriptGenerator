package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;

public class ButtonMemberSwap extends JButton{

	protected WindowGameManage mainWindow;
	
	private boolean participation;

	public ButtonMemberSwap(WindowGameManage mainWindow, String title, boolean participationToSet){
		super(title);

		this.mainWindow = mainWindow;
		this.participation = participationToSet;

		this.addActionListener(actionEvent -> {
			System.out.println("Setting " + this.mainWindow.getSelectedMembers().length + " members to participation = " + this.participation);
			
			for(Member m : this.mainWindow.getSelectedMembers()){
				m.setParticipating(this.participation);
			}
			
			this.mainWindow.updateListPanels();
		});
		
		this.setEnabled(false);
	}
	
	public void update(){
		Member[] selected = this.mainWindow.getSelectedMembers();
		if(selected.length == 0){
			this.setEnabled(false);
		}
		else{
			this.setEnabled(selected[0].isParticipating() != this.participation);
		}
	}
}
