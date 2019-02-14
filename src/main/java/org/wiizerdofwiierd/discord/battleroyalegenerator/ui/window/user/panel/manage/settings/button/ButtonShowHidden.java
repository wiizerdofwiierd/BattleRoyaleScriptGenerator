package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;

public class ButtonShowHidden extends JCheckBox{
	
	private PanelSettings settingsPanel;
	
	public ButtonShowHidden(PanelSettings settingsPanel){
		super("Show users not on server");
		this.settingsPanel = settingsPanel;

		this.setToolTipText("Toggle whether members not on the server are shown in the list");
		this.setHorizontalAlignment(SwingConstants.CENTER);

		this.addActionListener(actionEvent -> {
			this.getSettingsPanel().getParentFrame().getSettings().setShowHidden(this.isSelected());
			this.settingsPanel.getParentFrame().update();
		});
		
		this.setSelected(this.settingsPanel.getParentFrame().getSettings().showHidden());
	}
	
	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
}
