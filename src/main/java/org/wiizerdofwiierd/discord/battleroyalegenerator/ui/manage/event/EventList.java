package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedEventsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.RowHoverListener;

import javax.swing.*;
import java.awt.*;

public class EventList extends JList<GameEvent> implements RowHoverListener{
	
	private EventContext context;
	private boolean selectionFilter;
	
	private EventListRenderer renderer;
	private EventListModel model;
	
	public EventList(EventContext context, boolean selectionFilter){
		this.context = context;
		this.selectionFilter = selectionFilter;
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.renderer = new EventListRenderer();
		this.setCellRenderer(renderer);
		
		this.model = new EventListModel();
		this.setModel(model);
		
		this.registerHoverAdapters();
	}
	
	@Override
	public Rectangle getBoundsForRow(int row){
		return this.getCellBounds(row, row);
	}

	@Override
	public int getNumRows(){
		return this.getModel().getSize();
	}

	@Override
	public int getIndex(){
		return this.renderer.getHoveredIndex();
	}

	@Override
	public void setIndex(int index){
		this.renderer.setHoveredIndex(index);
		this.repaint();
	}

	@Override
	public void reset(){
		this.renderer.setHoveredIndex(-1);
		this.repaint();
	}
	
	public void update(){
		SavedEventsHandler handler = SavedEventsHandler.getInstance();
		
		this.model.clear();
		
		handler.getEvents().stream()
				.filter(e -> e.getContext() == this.context)
				.forEach(e -> this.model.addElement(e));
		
		this.updateUI();
	}
}
