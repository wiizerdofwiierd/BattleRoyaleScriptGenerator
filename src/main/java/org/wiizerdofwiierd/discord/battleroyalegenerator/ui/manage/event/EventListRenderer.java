package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.AbstractGameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.FatalGameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.theme.UIConstants;

import javax.swing.*;
import java.awt.*;

public class EventListRenderer implements ListCellRenderer<AbstractGameEvent>{
	
	private int hoveredIndex = -1;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends AbstractGameEvent> list, AbstractGameEvent event, int index, boolean isSelected, boolean hasFocus){
		
		EventListRendererPanel component = new EventListRendererPanel(event);
		
		if(isSelected){
			component.setBackground(UIConstants.Colors.ELEMENT_SELECTED);
		}
		else if(this.hoveredIndex == index){
			component.setBackground(UIConstants.Colors.ELEMENT_HOVERED);
			component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		else if(event instanceof FatalGameEvent){
			component.setBackground(UIConstants.Colors.TABLE_EVENT_FATAL);
		}
		else{
			component.setBackground(UIConstants.Colors.TABLE_EVENT_BACKGROUND);
		}
		
		return component;
	}
	
	public int getHoveredIndex(){
		return this.hoveredIndex;
	}
	
	public void setHoveredIndex(int index){
		this.hoveredIndex = index;
	}
}
