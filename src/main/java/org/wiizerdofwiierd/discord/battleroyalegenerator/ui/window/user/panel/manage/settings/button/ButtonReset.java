package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;

public class ButtonReset extends JButton{
	
	private static final String TEXT_RESET = "Reset selected members";
	private static final String TEXT_REMOVE = "Remove custom members";
	private static final String TEXT_MIXED = "Reset selected/remove custom members";
	
	private PanelSettings settingsPanel;
	
	public ButtonReset(PanelSettings settingsPanel){
		super("Reset selected members");
		this.settingsPanel = settingsPanel;
		
		this.setEnabled(false);

		this.addActionListener(actionEvent -> {
			WindowUserSelect mainWindow = this.settingsPanel.getParentFrame();

			for(Member m : mainWindow.getSelectedMembers()){
				
				if(m.isCustom()){
					mainWindow.getSettings().getMembers().remove(m);
				}
				else{
					m.restoreInfo(mainWindow.getClient().getUserByID(m.getId()), mainWindow.getGuild());
				}
			}

			mainWindow.update();
		});
	}
	
	public void update(){
		Member[] selected = this.settingsPanel.getParentFrame().getSelectedMembers();
		
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
