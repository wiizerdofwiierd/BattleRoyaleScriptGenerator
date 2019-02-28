package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.event.EventContext;

import javax.swing.*;

public class PanelEventList extends JPanel{
	
	private EventContext context;
	
	public PanelEventList(EventContext context){
		this.context = context;
		
		
	}
	
	public EventContext getContext(){
		return this.context;
	}
}
