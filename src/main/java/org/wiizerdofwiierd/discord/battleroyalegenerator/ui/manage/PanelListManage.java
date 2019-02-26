package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import javax.swing.*;
import java.awt.*;

public class PanelListManage extends JPanel{
	
	protected WindowGameManage mainWindow;
	protected PanelSettings settingsPanel;
	
	private ButtonMemberSwap addButton;
	private ButtonMemberSwap removeButton;
	
	public PanelListManage(WindowGameManage mainWindow){
		this.mainWindow = mainWindow;
		
		this.setLayout(new GridLayout(3, 1));
		
		GridBagLayout buttonFrameLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.8;
		c.weightx = 1.0;
		c.insets = new Insets(0, 45, 0, 45);

		JPanel addButtonPanel = new JPanel();
		addButtonPanel.setLayout(buttonFrameLayout);
		this.addButton = new ButtonMemberSwap(this.mainWindow, ">>", true);
		addButtonPanel.add(addButton, c);
		this.add(addButtonPanel);

		JPanel removeButtonPanel = new JPanel();
		removeButtonPanel.setLayout(buttonFrameLayout);
		this.removeButton = new ButtonMemberSwap(this.mainWindow, "<<", false);
		removeButtonPanel.add(removeButton, c);
		this.add(removeButtonPanel);

		this.settingsPanel = new PanelSettings(this.mainWindow, this);
		this.add(this.settingsPanel, c);
	}
	
	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
	
	public void update(){
		this.addButton.update();
		this.removeButton.update();
		this.settingsPanel.buttonReset.update();
	}
}
