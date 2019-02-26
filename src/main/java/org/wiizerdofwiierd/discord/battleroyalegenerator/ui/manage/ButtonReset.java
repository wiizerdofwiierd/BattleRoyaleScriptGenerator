package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;

public class ButtonReset extends JButton{
	
	private static final String TEXT_RESET = "Reset selected members";
	private static final String TEXT_REMOVE = "Remove custom members";
	private static final String TEXT_MIXED = "Reset selected/remove custom members";
	
	protected WindowGameManage mainWindow;
	protected PanelSettings settingsPanel;
	
	public ButtonReset(WindowGameManage mainWindow, PanelSettings settingsPanel){
		super("Reset selected members");
		this.mainWindow = mainWindow;
		this.settingsPanel = settingsPanel;
		
		this.setEnabled(false);

		this.addActionListener(actionEvent -> {
			for(Member m : this.mainWindow.getSelectedMembers()){
				
				if(m.isCustom()){
					this.mainWindow.getSettings().getMembers().remove(m);
				}
				else{
					m.restoreInfo(this.mainWindow.getClient().getUserByID(m.getId()), this.mainWindow.getGuild());
				}
			}

			mainWindow.updateListPanels();
		});
	}
	
	public void update(){
		Member[] selected = this.mainWindow.getSelectedMembers();
		
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
