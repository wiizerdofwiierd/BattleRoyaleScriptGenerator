package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;
import java.awt.*;

public class PanelEventList extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	public PanelEventList(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;
		
		this.setBorder(BorderFactory.createTitledBorder("All events"));
		this.setLayout(new GridBagLayout());
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		//Inner panel used to set a single border around both the list and the button
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());
		innerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(innerPanel, c);
		
		//List of all events
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0.95;
		innerPanel.add(new EventList(false), c);
		
		//Create event button
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.05;
		innerPanel.add( new JButton("Create new..."), c);
	}
}
