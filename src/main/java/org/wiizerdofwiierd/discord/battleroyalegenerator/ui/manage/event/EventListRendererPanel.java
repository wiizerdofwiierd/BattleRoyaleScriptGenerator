package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.AbstractGameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.FatalGameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.FatalScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.TributeInfo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventListRendererPanel extends JPanel{
	
	public EventListRendererPanel(AbstractGameEvent event){
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		JLabel name = new JLabel(event.getName());
		name.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		this.add(name, c);
		
		JLabel tributeCount = new JLabel("Tributes: " + event.getScenario().getNumTributes());
		tributeCount.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 1;
		c.gridy = 0;
		this.add(tributeCount, c);
		
		
		JLabel text = new JLabel(event.getScenario().getEventText());
		text.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(text, c);
		
		//Stop here if it's not a fatal event
		if(!(event instanceof FatalGameEvent)) return;

		List<String> killedList = new ArrayList<>();
		List<String> killersList = new ArrayList<>();
		
		FatalScenario scenario = (FatalScenario) event.getScenario();
		for(int i = 0;i < scenario.getNumTributes();i++){
			TributeInfo info = scenario.getKillerInfo(i);
			
			if(info.isKilled()){
				killedList.add("" + (i + 1));
			}
			
			if(info.isKiller()){
				killersList.add("" + (i + 1));
			}
		}
		
		JLabel killed = new JLabel("Killed: " + String.join(", ", killedList));
		killed.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(killed, c);
		
		JLabel killers = new JLabel("Killers: " + String.join(", ", killersList));
		killers.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 1;
		c.gridy = 3;
		this.add(killers, c);
	}
}
