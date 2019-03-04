package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.*;

public abstract class AbstractEventListPanel extends JPanel{
	
	private PanelManageEvents eventsPanel;
	private PanelEventDetails detailsPanel;
	private EventList list;
	
	public AbstractEventListPanel(PanelManageEvents eventsPanel, boolean selectionFilter){
		this.eventsPanel = eventsPanel;
		this.list = new EventList(eventsPanel.getContext(), selectionFilter);
		
		this.list.addListSelectionListener(e -> detailsPanel.showDetails(this.list.getSelectedValue()));
	}
	
	public PanelManageEvents getEventsPanel(){
		return this.eventsPanel;
	}
	
	public EventList getEventList(){
		return this.list;
	}
	
	public PanelEventDetails getDetailsPanel(){
		return this.detailsPanel;
	}
	
	public void setDetailsPanel(PanelEventDetails detailsPanel){
		this.detailsPanel = detailsPanel;
	}
	
	public void update(){
		this.list.updateUI();
		this.repaint();
	}
}
