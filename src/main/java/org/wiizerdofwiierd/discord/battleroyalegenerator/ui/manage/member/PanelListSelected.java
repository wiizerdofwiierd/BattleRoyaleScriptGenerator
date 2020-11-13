package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.TributeList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelListSelected extends AbstractMemberListPanel{
	
	protected PanelGenerate generatePanel;
	
	private TitledBorder border;
	
	public PanelListSelected(PanelManageTributes tributesPanel){
		super(tributesPanel, "Members Participating", true);
		
		this.border = BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP);
		this.setBorder(border);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 0.01;
		c.insets = new Insets(5, 0, 0, 0);

		this.generatePanel = new PanelGenerate(this.tributesPanel, this);
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(generatePanel, c);
	}
	
	public PanelGenerate getGeneratePanel(){
		return this.generatePanel;
	}
	
	@Override
	public void update(){
		super.update();
		
		TributeList members = this.tributesPanel.getMembers();
		Settings settings = this.tributesPanel.getSettings();
		
		int selected = members.getMembersByParticipation(true, settings).size();
		int required = this.tributesPanel.getSettings().castSize.getValue().getNumPlayers();
		
		this.border.setTitle(String.format("Members Participating (%d/%d)", selected, required));
		this.repaint();
		
		this.generatePanel.update();
	}
}
