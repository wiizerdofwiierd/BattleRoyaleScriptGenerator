package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.settings.PanelSettings;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ButtonRandomize extends JButton{
	
	private PanelSettings settingsPanel;
	
	public ButtonRandomize(PanelSettings settingsPanel){
		super("Randomize");
		this.settingsPanel = settingsPanel;

		this.addActionListener(actionEvent -> {
			WindowUserSelect mainWindow = this.settingsPanel.getParentFrame();
			Settings settings = mainWindow.getSettings();
			
			settings.getMembers().forEach(m -> m.setParticipating(false));	//Set each member's participation to false
			List<Member> members = settings.getMembers().stream()
					.filter(m -> !m.isBot() || settings.showBots())			//Filter out bots according to our settings
					.filter(m -> !m.isCustom() || settings.showCustom())	//Filter out custom users according to our settings
					.filter(m -> !m.isHidden() || settings.showHidden())	//Filter out hidden users according to our settings
					.collect(Collectors.toList());
			
			new Random().ints(0, members.size())							//Generate random ints in [0, size of members list)
					.distinct()												//Limit to unique values
					.limit(settings.getCastSize().getNumPlayers())			//Limit to selected cast size
					.mapToObj(members::get)									//Map each int to its corresponding member
					.forEach(m -> m.setParticipating(true));				//Set each remaining member's participation to true
			
			mainWindow.update();
		});
	}
}
