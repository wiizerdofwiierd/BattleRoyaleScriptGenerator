package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelListSelected extends AbstractListPanel{
	
	protected PanelGenerate generatePanel;
	
	private TitledBorder border;
	
	public PanelListSelected(WindowGameManage mainWindow){
		super(mainWindow, "Members Participating", true);
		
		this.border = BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP);
		this.setBorder(border);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 0.01;
		c.insets = new Insets(5, 0, 0, 0);

		this.generatePanel = new PanelGenerate(this.mainWindow, this);
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(generatePanel, c);
	}
	
	public PanelGenerate getGeneratePanel(){
		return this.generatePanel;
	}
	
	@Override
	public void update(){
		super.update();
		
		MemberList members = this.mainWindow.getMembers();
		Settings settings = this.mainWindow.getSettings();
		
		int selected = members.getMembersByParticipation(true, settings).size();
		int required = this.mainWindow.getSettings().castSize.getValue().getNumPlayers();
		
		this.border.setTitle(String.format("Members Participating (%d/%d)", selected, required));
		this.repaint();
		
		this.generatePanel.update();
	}
}
