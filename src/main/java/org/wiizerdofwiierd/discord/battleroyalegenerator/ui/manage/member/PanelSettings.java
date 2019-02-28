package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import javax.swing.*;
import java.awt.*;

public class PanelSettings extends JPanel{
	
	protected PanelManageTributes tributesPanel;
	protected PanelListManage swapPanel;
	
	protected ButtonRandomize buttonRandomize;
	protected ButtonReset buttonReset;
	
	public PanelSettings(PanelManageTributes tributesPanel, PanelListManage swapPanel){
		this.tributesPanel = tributesPanel;
		this.swapPanel = swapPanel;

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		ButtonSettingToggle buttonShowBots = new ButtonSettingToggle(
				"Show Bots", 
				"Toggle whether Bot users are shown in the list",
				tributesPanel.getSettings().showBots
		);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonShowBots, c);

		ButtonSettingToggle buttonShowCustom = new ButtonSettingToggle(
				"Show custom", 
				"Toggle whether custom users are shown in the list",
				tributesPanel.getSettings().showCustom);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonShowCustom, c);
		
		this.buttonRandomize = new ButtonRandomize(this.tributesPanel, this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weighty = 0.15;
		c.insets = new Insets(0, 32, 6, 32);
		this.add(buttonRandomize, c);
		
		this.buttonReset = new ButtonReset(this.tributesPanel, this);
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