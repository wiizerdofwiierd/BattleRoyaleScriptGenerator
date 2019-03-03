package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import javax.swing.*;
import java.awt.*;

public class PanelListManage extends JPanel{
	
	private PanelManageTributes tributesPanel;
	private PanelSettings settingsPanel;
	
	private ButtonMemberSwap addButton;
	private ButtonMemberSwap removeButton;
	
	public PanelListManage(PanelManageTributes tributesPanel){
		this.tributesPanel = tributesPanel;
		
		this.setLayout(new GridLayout(3, 1));
		
		GridBagLayout buttonFrameLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.8;
		c.weightx = 1.0;
		c.insets = new Insets(0, 45, 0, 45);

		JPanel addButtonPanel = new JPanel();
		addButtonPanel.setLayout(buttonFrameLayout);
		this.addButton = new ButtonMemberSwap(this.tributesPanel, ">>", true);
		addButtonPanel.add(addButton, c);
		this.add(addButtonPanel);

		JPanel removeButtonPanel = new JPanel();
		removeButtonPanel.setLayout(buttonFrameLayout);
		this.removeButton = new ButtonMemberSwap(this.tributesPanel, "<<", false);
		removeButtonPanel.add(removeButton, c);
		this.add(removeButtonPanel);

		this.settingsPanel = new PanelSettings(this.tributesPanel, this);
		this.add(this.settingsPanel, c);
	}
	
	public PanelSettings getSettingsPanel(){
		return this.settingsPanel;
	}
	
	public void update(){
		this.addButton.update();
		this.removeButton.update();
		this.settingsPanel.getResetButton().update();
	}
}
