package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.AbstractBotPanel;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.button.ButtonMemberSwap;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;
import java.awt.*;

public class PanelMemberManage extends AbstractBotPanel{
	
	private PanelSettings settingsPanel;
	
	public PanelMemberManage(WindowUserSelect parentFrame){
		super(parentFrame);
		
		this.setLayout(new GridLayout(3, 1));
		
		GridBagLayout buttonFrameLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.8;
		c.weightx = 1.0;
		c.insets = new Insets(0, 45, 0, 45);

		JPanel addButtonPanel = new JPanel();
		addButtonPanel.setLayout(buttonFrameLayout);
		ButtonMemberSwap addButton = new ButtonMemberSwap(this.parentFrame, ">>", true);
		addButtonPanel.add(addButton, c);
		this.add(addButtonPanel);

		JPanel removeButtonPanel = new JPanel();
		removeButtonPanel.setLayout(buttonFrameLayout);
		ButtonMemberSwap removeButton = new ButtonMemberSwap(this.parentFrame, "<<", false);
		removeButtonPanel.add(removeButton, c);
		this.add(removeButtonPanel);

		this.settingsPanel = new PanelSettings(this);
		this.add(settingsPanel, c);
	}

	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
	
	@Override
	public void update(){
		this.settingsPanel.update();
	}
}
