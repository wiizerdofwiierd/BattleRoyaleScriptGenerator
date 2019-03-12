package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEventFactory;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedEventsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.SubtleScrollPane;

import javax.swing.*;
import java.awt.*;

public class PanelEventList extends AbstractEventListPanel{

	public PanelEventList(PanelManageEvents eventsPanel){
		super(eventsPanel,false);

		this.setBorder(BorderFactory.createTitledBorder("All events"));
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill    = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;

		//Inner panel used to set a single border around both the list and the button
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());
		innerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		this.add(innerPanel, c);
		
		
		//Panel for displaying events
		EventList eventList = getEventList();
		
		c.gridx   = 0;
		c.gridy   = 0;
		c.weighty = 0.94;

		innerPanel.add(new SubtleScrollPane(eventList), c);
		
		//Create event button
		JButton buttonCreateEvent = new JButton("Create new...");
		buttonCreateEvent.addActionListener(actionEvent -> {
			SavedEventsHandler handler = SavedEventsHandler.getInstance();
			
			var newEvent = GameEventFactory.createEvent(getEventsPanel().getContext());
			
			handler.addEvent(newEvent);
			handler.save();
			
			eventList.update();
			eventList.setSelectedValue(newEvent, true);
		});
		
		c.gridx   = 0;
		c.gridy   = 1;
		c.weighty = 0.05;
		
		innerPanel.add(buttonCreateEvent, c);

		//Delete event button
		JButton buttonDeleteEvent = new JButton("Delete");
		buttonDeleteEvent.addActionListener(actionEvent -> {
			SavedEventsHandler handler = SavedEventsHandler.getInstance();
			
			handler.deleteEvent(eventList.getSelectedValue());
			handler.save();

			eventList.update();
			eventList.setSelectedIndex(eventList.getSelectedIndex() - 1);
		});
		
		c.gridx   = 0;
		c.gridy   = 2;
		c.weighty = 0.01;
		
		innerPanel.add(buttonDeleteEvent, c);
		
		eventList.update();
	}
}
