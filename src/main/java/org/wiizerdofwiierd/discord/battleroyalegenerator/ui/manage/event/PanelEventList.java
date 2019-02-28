package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;

public class PanelEventList extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	public PanelEventList(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;
		
		this.setBorder(BorderFactory.createTitledBorder("All events"));
	}
}
