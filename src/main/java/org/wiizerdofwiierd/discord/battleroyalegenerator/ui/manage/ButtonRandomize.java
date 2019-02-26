package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ButtonRandomize extends JButton{
	
	protected WindowGameManage mainWindow;
	protected PanelSettings settingsPanel;
	
	public ButtonRandomize(WindowGameManage mainWindow, PanelSettings settingsPanel){
		super("Randomize");
		this.mainWindow = mainWindow;
		this.settingsPanel = settingsPanel;

		this.addActionListener(actionEvent -> {
			Settings settings = mainWindow.getSettings();
			
			boolean showBots = settings.showBots.getValue();
			boolean showCustom = settings.showCustom.getValue();
			
			CastSize castSize = settings.castSize.getValue();
			
			settings.getMembers().forEach(m -> m.setParticipating(false));	//Set each member's participation to false
			List<Member> members = settings.getMembers().stream()
					.filter(m -> !m.isBot() || showBots)					//Filter out bots according to our settings
					.filter(m -> !m.isCustom() || showCustom)				//Filter out custom users according to our settings
					.collect(Collectors.toList());
			
			new Random().ints(0, members.size())							//Generate random ints in [0, size of members list)
					.distinct()												//Limit to unique values
					.limit(castSize.getNumPlayers())						//Limit to selected cast size
					.mapToObj(members::get)									//Map each int to its corresponding member
					.forEach(m -> m.setParticipating(true));				//Set each remaining member's participation to true
			
			mainWindow.updateListPanels();
		});
	}
}
