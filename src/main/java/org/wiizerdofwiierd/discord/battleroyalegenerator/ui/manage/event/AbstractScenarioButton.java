package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;

public abstract class AbstractScenarioButton extends JButton{
	
	protected PanelEventDetails detailsPanel;
	protected JTabbedPane tabbedPane;
	protected GameEvent event;
	
	public AbstractScenarioButton(String text, PanelEventDetails detailsPanel, JTabbedPane tabbedPane, GameEvent event){
		super(text);
		this.detailsPanel = detailsPanel;
		this.tabbedPane = tabbedPane;
		this.event = event;
	}
	
	public abstract void update();
}
