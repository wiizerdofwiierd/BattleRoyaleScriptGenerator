package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;

public class ButtonScenarioAdd extends AbstractScenarioButton{
	
	public ButtonScenarioAdd(PanelEventDetails detailsPanel, JTabbedPane tabbedPane, GameEvent event){
		super("Add", detailsPanel, tabbedPane, event);

		this.addActionListener(actionEvent -> {
			this.event.getScenarios().add(new EventScenario());
			this.detailsPanel.refresh();
		});
		
		this.update();
	}

	@Override
	public void update(){
		//Disable this button if we've reached the maximum allowed scenarios
		this.setEnabled(this.event.getScenarios().size() < GameEvent.MAX_SCENARIOS);
	}
}
