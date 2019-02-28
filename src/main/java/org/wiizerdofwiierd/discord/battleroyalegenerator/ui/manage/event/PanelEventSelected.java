package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;

public class PanelEventSelected extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	public PanelEventSelected(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;

		this.setBorder(BorderFactory.createTitledBorder("Selected events"));
	}
}
