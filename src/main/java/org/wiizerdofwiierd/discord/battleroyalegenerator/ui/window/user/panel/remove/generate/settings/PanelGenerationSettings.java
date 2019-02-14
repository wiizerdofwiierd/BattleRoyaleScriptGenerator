package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.button.ButtonCopyURL;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.button.ButtonGenerateURL;

import javax.swing.*;
import java.awt.*;

public class PanelGenerationSettings extends JPanel{
	
	private PanelGenerate generatePanel;
	
	private ButtonGenerateURL buttonGenerateUrl;
	private ButtonCopyURL buttonCopyUrl;
	
	public PanelGenerationSettings(PanelGenerate generatePanel){
		this.generatePanel = generatePanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		this.buttonGenerateUrl = new ButtonGenerateURL(this);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonGenerateUrl, c);

		this.buttonCopyUrl = new ButtonCopyURL(this);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonCopyUrl , c);
	}
	
	public PanelGenerate getGeneratePanel(){
		return this.generatePanel;
	}
	
	public ButtonGenerateURL getGenerateURLButton(){
		return this.buttonGenerateUrl;
	}
	
	public ButtonCopyURL getCopyURLButton(){
		return this.buttonCopyUrl;
	}
	
	public Settings getSettings(){
		return this.generatePanel.getParentFrame().getSettings();
	}
}
