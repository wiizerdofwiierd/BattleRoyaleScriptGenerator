package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ButtonRandomize extends JButton{
	
	private PanelManageTributes tributesPanel;
	private PanelSettings settingsPanel;
	
	public ButtonRandomize(PanelManageTributes tributesPanel, PanelSettings settingsPanel){
		super("Randomize");
		this.tributesPanel = tributesPanel;
		this.settingsPanel = settingsPanel;

		this.addActionListener(actionEvent -> {
			Settings settings = tributesPanel.getSettings();
			
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

			tributesPanel.updateMemberLists();
			tributesPanel.getMainWindow().setStatusBarText("Members randomized");
		});
	}
}
