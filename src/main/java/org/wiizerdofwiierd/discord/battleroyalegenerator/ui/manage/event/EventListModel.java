package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EventListModel extends AbstractListModel<GameEvent>{
	
	private List<GameEvent> events;
	
	public EventListModel(){
		this.events = new ArrayList<>();
	}
	
	public void addElement(GameEvent event){
		this.events.add(event);
	}
	
	public void clear(){
		this.events.clear();
	}
	
	@Override
	public int getSize(){
		return this.events.size();
	}

	@Override
	public GameEvent getElementAt(int index){
		return this.events.get(index);
	}
}
