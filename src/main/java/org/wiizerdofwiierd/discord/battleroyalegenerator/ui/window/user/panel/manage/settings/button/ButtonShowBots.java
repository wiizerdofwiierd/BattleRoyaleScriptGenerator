package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;

public class ButtonShowBots extends JCheckBox{
	
	private PanelSettings settingsPanel;
	
	public ButtonShowBots(PanelSettings settingsPanel){
		super("Show Bots");
		this.settingsPanel = settingsPanel;
		
		this.setToolTipText("Toggle whether Bot users are shown in the list");
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addActionListener(actionEvent -> {
			this.getSettingsPanel().getParentFrame().getSettings().setShowBots(this.isSelected());
			this.settingsPanel.getParentFrame().update();
		});
		
		this.setSelected(this.settingsPanel.getParentFrame().getSettings().showBots());
	}
	
	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
}
