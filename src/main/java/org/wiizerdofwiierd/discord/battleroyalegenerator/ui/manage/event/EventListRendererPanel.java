package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventListRendererPanel extends JPanel{
	
	public EventListRendererPanel(GameEvent event){
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		JLabel name = new JLabel(event.getName());
		name.setHorizontalAlignment(SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		this.add(name, c);
		
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		
		int gridy = 2;
		EventScenario[] scenarios = event.getScenarios();
		for(int i = 0;i < scenarios.length;i++){
			EventScenario s = scenarios[i];
			
			JLabel tributeCount = new JLabel("Tributes: " + s.getNumTributes());
			tributeCount.setHorizontalAlignment(SwingConstants.CENTER);
			c.gridx = 0;
			c.gridy = ++gridy;
			c.gridwidth = 2;
			this.add(tributeCount, c);
			
			JLabel text = new JLabel(s.getText());
			text.setHorizontalAlignment(SwingConstants.CENTER);
			c.gridx = 0;
			c.gridy = ++gridy;
			c.gridwidth = 2;
			this.add(text, c);
			
			if(s.isFatal()){
				gridy++;
				
				List<String> killedList = new ArrayList<>();
				Arrays.stream(s.getKillerInfo().getKilled()).forEach(x -> killedList.add("" + (x + 1)));

				List<String> killersList = new ArrayList<>();
				Arrays.stream(s.getKillerInfo().getKillers()).forEach(x -> killersList.add("" + (x + 1)));
				
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
			
			if(i < scenarios.length - 1){
				c.gridx = 0;
				c.gridy = ++gridy;
				c.gridwidth = 2;
				this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
			}
			
			gridy++;
		}
	}
}
