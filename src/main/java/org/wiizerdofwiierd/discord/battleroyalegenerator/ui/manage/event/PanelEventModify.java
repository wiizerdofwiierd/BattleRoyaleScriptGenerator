package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;

public class PanelEventModify extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	public PanelEventModify(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;

		this.setBorder(BorderFactory.createTitledBorder("Event details"));
	}
	
	public void update(){
		
	}
}
