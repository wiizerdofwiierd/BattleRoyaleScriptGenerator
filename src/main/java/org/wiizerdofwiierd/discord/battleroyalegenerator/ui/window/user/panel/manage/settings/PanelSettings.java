package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.AbstractBotPanel;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button.*;

import java.awt.*;

public class PanelSettings extends AbstractBotPanel{

	private ButtonShowBots buttonShowBots;
	private ButtonShowHidden buttonShowHidden;
	
	private ButtonRandomize buttonRandomize;
	private ButtonReset buttonReset;
	
	public PanelSettings(AbstractBotPanel parent){
		super(parent);

		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		this.buttonShowBots = new ButtonShowBots(this);
		c.gridx = 0;
		c.gridy = 0;
		this.add(buttonShowBots, c);

		ButtonShowCustom buttonShowCustom = new ButtonShowCustom(this);
		c.gridx = 1;
		c.gridy = 0;
		this.add(buttonShowCustom, c);
		
		this.buttonShowHidden = new ButtonShowHidden(this);
		c.gridx = 2;
		c.gridy = 0;
		this.add(buttonShowHidden, c);
		
		this.buttonRandomize = new ButtonRandomize(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		c.weighty = 0.15;
		c.insets = new Insets(0, 32, 6, 32);
		this.add(buttonRandomize, c);
		
		this.buttonReset = new ButtonReset(this);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		c.weighty = 0.15;
		c.insets = new Insets(0, 32, 6, 32);
		this.add(buttonReset, c);
		
		
	}

	public ButtonReset getResetButton(){
		return this.buttonReset;
	}
	
	@Override
	public void update(){
		
	}
}