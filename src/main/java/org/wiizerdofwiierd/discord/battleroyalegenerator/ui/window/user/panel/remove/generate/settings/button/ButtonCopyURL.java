package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.PanelGenerationSettings;

import javax.swing.*;

public class ButtonCopyURL extends JCheckBox{

	private PanelGenerationSettings settingsPanel;
	
	public ButtonCopyURL(PanelGenerationSettings settingsPanel){
		super("Copy link to clipboard");
		this.settingsPanel = settingsPanel;

		this.setToolTipText("Automatically copy generated link to the clipboard");
		this.setHorizontalAlignment(SwingConstants.CENTER);

		this.addActionListener(actionEvent -> {
			this.settingsPanel.getSettings().setAutoCopyLink(this.isSelected());
		});

		this.setSelected(this.settingsPanel.getSettings().autoCopyLink());
		this.setEnabled(this.settingsPanel.getSettings().autoGenerateURL());
	}
}
