package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;

import javax.swing.*;
import java.awt.*;

public class PanelGenerationSettings extends JPanel{
	
	private WindowGameManage mainWindow;
	private PanelGenerate generatePanel;
	
	public PanelGenerationSettings(WindowGameManage mainWindow, PanelGenerate generatePanel){
		this.mainWindow = mainWindow;
		this.generatePanel = generatePanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		ButtonSettingToggle buttonGenerateUrl = new ButtonSettingToggle(
				this.mainWindow, 
				"Generate BrantSteele Hunger Games link",
				"Automatically generate a link to this game on the BrantSteele Hunger Games site",
				this.mainWindow.getSettings().autoGenerateLink
		);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonGenerateUrl, c);

		ButtonSettingToggle buttonCopyUrl = new ButtonSettingToggle(
				this.mainWindow,
				"Copy link to clipboard",
				"Automatically copy generated link to the clipboard",
				this.mainWindow.getSettings().autoCopyLink
		);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonCopyUrl , c);

		// Enable/Disable copy button according to the setting of the first button
		this.mainWindow.getSettings().autoGenerateLink.addChangeListener((oldValue, newValue) -> buttonCopyUrl.setEnabled(newValue));
	}
	
	public Settings getSettings(){
		return this.mainWindow.getSettings();
	}
	
	public void update(){}
	
}
