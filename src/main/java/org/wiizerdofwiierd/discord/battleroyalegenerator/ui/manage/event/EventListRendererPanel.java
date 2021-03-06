package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventListRendererPanel extends JPanel{
	
	public EventListRendererPanel(GameEvent event){
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		boolean isArenaEvent = event.getContext() == EventContext.ARENA;
		//If the event is an arena event, use the name. Otherwise, display the scenario text
		String effectiveName;
		if(isArenaEvent)
			effectiveName = event.getName();
		else 
			effectiveName = event.getScenarios().get(0).getText();
		
		JLabel name = new JLabel(effectiveName);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(name, c);
		
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		
		int gridy = 2;
		List<EventScenario> scenarios = event.getScenarios();
		for(int i = 0;i < scenarios.size();i++){
			EventScenario s = scenarios.get(i);
			
			JLabel tributeCount = new JLabel("Tributes: " + s.getNumTributes());
			tributeCount.setHorizontalAlignment(SwingConstants.CENTER);
			c.gridx = 0;
			c.gridy = ++gridy;
			c.gridwidth = 2;
			this.add(tributeCount, c);
			
			if(isArenaEvent){
				//Scenario text
				JLabel text = new JLabel(s.getText());
				text.setHorizontalAlignment(SwingConstants.CENTER);
				
				c.gridx = 0;
				c.gridy = ++gridy;
				c.gridwidth = 2;
				
				this.add(text, c);
			}
			
			if(s.isFatal()){
				gridy++;
				
				List<String> killedList = new ArrayList<>();
				s.getKilled().forEach(x -> killedList.add("" + x));

				List<String> killersList = new ArrayList<>();
				s.getKillers().forEach(x -> killersList.add("" + x));
				
				JLabel killed = new JLabel("Killed: " + String.join(", ", killedList));
				killed.setHorizontalAlignment(SwingConstants.CENTER);
				c.gridx = 0;
				c.gridy = gridy;
				c.gridwidth = 1;
				this.add(killed, c);

				JLabel killers = new JLabel("Killers: " + String.join(", ", killersList));
				killers.setHorizontalAlignment(SwingConstants.CENTER);
				c.gridx = 1;
				c.gridy = gridy;
				c.gridwidth = 1;
				this.add(killers, c);
			}
			
			if(i < scenarios.size() - 1){
				c.gridx = 0;
				c.gridy = ++gridy;
				c.gridwidth = 2;
				this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
			}
			
			gridy++;
		}
	}
}
