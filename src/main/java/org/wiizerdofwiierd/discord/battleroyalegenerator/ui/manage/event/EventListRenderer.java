package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.theme.UIConstants;

import javax.swing.*;
import java.awt.*;

public class EventListRenderer implements ListCellRenderer<GameEvent>{
	
	private int hoveredIndex = -1;
	
	@Override
	public Component getListCellRendererComponent(JList<? extends GameEvent> list, GameEvent event, int index, boolean isSelected, boolean hasFocus){
		
		EventListRendererPanel component = new EventListRendererPanel(event);
		
		if(isSelected){
			component.setBackground(UIConstants.Colors.ELEMENT_SELECTED);
		}
		else if(this.hoveredIndex == index){
			component.setBackground(UIConstants.Colors.ELEMENT_HOVERED);
			component.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		else if(event.isFatal()){
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
