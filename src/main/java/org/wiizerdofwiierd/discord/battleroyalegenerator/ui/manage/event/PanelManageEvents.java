package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.WindowGameManage;

import javax.swing.*;
import java.awt.*;

public class PanelManageEvents extends JPanel{
	
	private WindowGameManage mainWindow;
	private EventContext context;
	
	private PanelEventList eventListPanel;
	private PanelEventSelected eventsUsedPanel;
	private PanelEventDetails eventDetailsPanel;
	
	private GameEvent selectedEvent;
	
	public PanelManageEvents(WindowGameManage mainWindow, EventContext context){
		this.mainWindow = mainWindow;
		this.context = context;

		this.setBorder(BorderFactory.createTitledBorder("Manage " + context.getName() + " Events"));
		this.setLayout(new GridLayout(1, 3));
		
		//Left panel (List all custom events in this context)
		this.eventListPanel = new PanelEventList(this);
		this.add(eventListPanel);
		
		//Center panel (List events being used in this game)
		this.eventsUsedPanel = new PanelEventSelected(this);
		this.add(eventsUsedPanel);
		
		//Right panel (Show details of currently selected event)
		this.eventDetailsPanel = new PanelEventDetails(this);
		this.add(eventDetailsPanel);
	}
	
	public WindowGameManage getMainWindow(){
		return this.mainWindow;
	}
	
	public PanelEventList getEventListPanel(){
		return this.eventListPanel;
	}
	
	public PanelEventSelected getEventsUsedPanel(){
		return this.eventsUsedPanel;
	}
	
	public PanelEventDetails getEventDetailsPanel(){
		return this.eventDetailsPanel;
	}
	
	public EventContext getContext(){
		return this.context;
	}
	
	public GameEvent getSelectedEvent(){
		return this.selectedEvent;
	}
	
	public void setSelectedEvent(GameEvent event){
		this.selectedEvent = event;
		this.eventDetailsPanel.showDetails(event);
	}
	
	public void update(){
		this.eventListPanel.update();
	}
}
