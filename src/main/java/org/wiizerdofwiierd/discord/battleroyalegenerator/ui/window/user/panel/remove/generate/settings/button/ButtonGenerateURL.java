package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.PanelGenerationSettings;

import javax.swing.*;

public class ButtonGenerateURL extends JCheckBox{

	private PanelGenerationSettings settingsPanel;
	
	public ButtonGenerateURL(PanelGenerationSettings settingsPanel){
		super("Generate BrantSteele Hunger Games link");
		this.settingsPanel = settingsPanel;

		this.setToolTipText("Automatically generate a link to this game on the BrantSteele Hunger Games site");
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addActionListener(actionEvent -> {
			this.settingsPanel.getSettings().setAutoGenerateUrl(this.isSelected());
			
			this.settingsPanel.getCopyURLButton().setEnabled(this.isSelected());
		});
		
		this.setSelected(this.settingsPanel.getSettings().autoGenerateURL());
	}
}
