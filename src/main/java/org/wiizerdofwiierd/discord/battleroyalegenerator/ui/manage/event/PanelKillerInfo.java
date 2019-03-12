package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;

import javax.swing.*;
import java.awt.*;

public class PanelKillerInfo extends JPanel{
	
	private static final int ROW_GAP = 32;
	
	public PanelKillerInfo(EventScenario scenario){
		this.setLayout(new GridLayout(0, 3, 0, ROW_GAP));

		//Tribute killer info
		for(int i = 0;i < scenario.getNumTributes();i++){
			final int tributeIndex = i;
			
			//Tribute number label
			JLabel tributeNum = new JLabel("Tribute " + (i + 1) + ": ");
			tributeNum.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(tributeNum);

			//Checkbox to toggle tribute killed status
			JCheckBox isKilled = new JCheckBox("Killed");
			isKilled.setHorizontalAlignment(SwingConstants.CENTER);
			isKilled.setSelected(scenario.isKilled(i));
			isKilled.addActionListener(actionEvent -> scenario.setKilled(tributeIndex, isKilled.isSelected()));
			this.add(isKilled);

			//Checkbox to toggle tribute killer status
			JCheckBox isKiller = new JCheckBox("Killer");
			isKiller.setHorizontalAlignment(SwingConstants.CENTER);
			isKiller.setSelected(scenario.isKiller(i));
			isKiller.addActionListener(actionEvent -> scenario.setKiller(tributeIndex, isKiller.isSelected()));
			this.add(isKiller);
		}
	}
}
