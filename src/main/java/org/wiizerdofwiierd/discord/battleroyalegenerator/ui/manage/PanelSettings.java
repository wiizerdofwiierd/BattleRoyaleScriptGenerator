package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import javax.swing.*;
import java.awt.*;

public class PanelSettings extends JPanel{
	
	protected WindowGameManage mainWindow;
	protected PanelListManage swapPanel;
	
	protected ButtonRandomize buttonRandomize;
	protected ButtonReset buttonReset;
	
	public PanelSettings(WindowGameManage mainWindow, PanelListManage swapPanel){
		this.mainWindow = mainWindow;
		this.swapPanel = swapPanel;

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		ButtonSettingToggle buttonShowBots = new ButtonSettingToggle(
				this.mainWindow, 
				"Show Bots", 
				"Toggle whether Bot users are shown in the list", 
				mainWindow.getSettings().showBots
		);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonShowBots, c);

		ButtonSettingToggle buttonShowCustom = new ButtonSettingToggle(
				this.mainWindow, 
				"Show custom", 
				"Toggle whether custom users are shown in the list",
				mainWindow.getSettings().showCustom);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonShowCustom, c);
		
		this.buttonRandomize = new ButtonRandomize(this.mainWindow, this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 0.15;
		c.insets = new Insets(0, 32, 6, 32);
		this.add(buttonRandomize, c);
		
		this.buttonReset = new ButtonReset(this.mainWindow, this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weighty = 0.15;
		c.insets = new Insets(0, 32, 6, 32);
		this.add(buttonReset, c);
	}
	
	public ButtonReset getResetButton(){
		return this.buttonReset;
	}
}