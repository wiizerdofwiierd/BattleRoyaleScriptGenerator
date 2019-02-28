package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;

import javax.swing.*;
import java.awt.*;

public class PanelGenerationSettings extends JPanel{
	
	private PanelManageTributes tributesPanel;
	
	public PanelGenerationSettings(PanelManageTributes tributesPanel){
		this.tributesPanel = tributesPanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		ButtonSettingToggle buttonGenerateUrl = new ButtonSettingToggle(
				"Generate BrantSteele Hunger Games link",
				"Automatically generate a link to this game on the BrantSteele Hunger Games site",
				this.tributesPanel.getSettings().autoGenerateLink
		);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonGenerateUrl, c);

		ButtonSettingToggle buttonCopyUrl = new ButtonSettingToggle(
				"Copy link to clipboard",
				"Automatically copy generated link to the clipboard",
				this.tributesPanel.getSettings().autoCopyLink
		);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonCopyUrl , c);

		//Enable/Disable copy button according to the setting of the first button
		this.tributesPanel.getSettings().autoGenerateLink.addChangeListener((oldValue, newValue) -> buttonCopyUrl.setEnabled(newValue));
	}
	
	public Settings getSettings(){
		return this.tributesPanel.getSettings();
	}
	
	public void update(){}
	
}
