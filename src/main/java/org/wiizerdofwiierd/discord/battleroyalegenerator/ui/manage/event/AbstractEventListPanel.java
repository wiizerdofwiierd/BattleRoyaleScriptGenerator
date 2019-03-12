package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;

public abstract class AbstractEventListPanel extends JPanel{
	
	private PanelManageEvents eventsPanel;
	private EventList list;
	
	public AbstractEventListPanel(PanelManageEvents eventsPanel, boolean selectionFilter){
		this.eventsPanel = eventsPanel;
		this.list = new EventList(eventsPanel.getContext(), selectionFilter);
		
		this.list.addListSelectionListener(e -> {
			if(e.getValueIsAdjusting()) return;
			eventsPanel.setSelectedEvent(this.list.getSelectedValue());
		});
	}
	
	public PanelManageEvents getEventsPanel(){
		return this.eventsPanel;
	}
	
	public EventList getEventList(){
		return this.list;
	}
	
	public void update(){
		this.list.update();
	}
}
