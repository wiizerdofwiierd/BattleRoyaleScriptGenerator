package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.AbstractMemberPanel;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PanelMemberRemove extends AbstractMemberPanel{
	
	private PanelGenerate generatePanel;
	
	public PanelMemberRemove (WindowUserSelect parentFrame, String title){
		super(parentFrame, title, true);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weighty = 0.01;
		c.insets = new Insets(5, 0, 0, 0);

		this.generatePanel = new PanelGenerate(this);
		generatePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(generatePanel, c);
	}
	
	public PanelGenerate getGeneratePanel(){
		return this.generatePanel;
	}
	
	@Override
	public void update(){
		super.update();
		
		MemberList members = this.parentFrame.getMembers();
		Settings settings = this.generatePanel.getParentFrame().getSettings();
		
		int selected = members.getMembersByParticipation(true, settings).size();
		int required = this.parentFrame.getSettings().getCastSize().getNumPlayers();
		
		//TODO: Cleanup handling of this
		TitledBorder border = BorderFactory.createTitledBorder(String.format("Members Participating (%d/%d)", selected, required));
		border.setTitleJustification(TitledBorder.CENTER);
		
		this.setBorder(border);
		
		this.generatePanel.update();
	}
}
