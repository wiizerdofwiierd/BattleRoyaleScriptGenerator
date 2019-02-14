package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;

public class ButtonShowCustom extends JCheckBox{

	private PanelSettings settingsPanel;

	public ButtonShowCustom(PanelSettings settingsPanel){
		super("Show custom");
		this.settingsPanel = settingsPanel;

		this.setToolTipText("Toggle whether custom users are shown in the list");
		this.setHorizontalAlignment(SwingConstants.CENTER);

		this.addActionListener(actionEvent -> {
			this.getSettingsPanel().getParentFrame().getSettings().setShowCustom(this.isSelected());
			this.settingsPanel.getParentFrame().update();
		});

		this.setSelected(this.settingsPanel.getParentFrame().getSettings().showCustom());
	}

	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
}
