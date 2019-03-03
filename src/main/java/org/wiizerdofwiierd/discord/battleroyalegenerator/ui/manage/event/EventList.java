package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.*;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.MouseAdapterListener;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.UpdatingMouseAdapter;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.UpdatingMouseMotionAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventList extends JList<AbstractGameEvent> implements MouseAdapterListener{
	
	private boolean selectionFilter;
	
	private EventListRenderer renderer;
	
	public EventList(boolean selectionFilter){
		this.selectionFilter = selectionFilter;
		List<AbstractGameEvent> events = new ArrayList<>();
		
		this.renderer = new EventListRenderer();
		this.setCellRenderer(renderer);
		
		events.add(new SimpleGameEvent("Event name", new SimpleScenario(1, "(Player1) picks flowers"), EventContext.ARENA));
		events.add(new FatalGameEvent(
				"Fatal event", 
				new FatalScenario(
						3, 
						"(Player1) kills (Player2) and (Player3) with an axe", 
						new TributeInfo(true, false), 
						new TributeInfo(false, true), 
						new TributeInfo(false, true)
				), EventContext.ARENA));
		this.setListData(events.toArray(new AbstractGameEvent[events.size()]));

		this.addMouseListener(new UpdatingMouseAdapter(this));
		this.addMouseMotionListener(new UpdatingMouseMotionAdapter(this));
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
}
