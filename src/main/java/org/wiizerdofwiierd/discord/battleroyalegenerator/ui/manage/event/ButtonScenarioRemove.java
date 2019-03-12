package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;

public class ButtonScenarioRemove extends AbstractScenarioButton{
	
	public ButtonScenarioRemove(PanelEventDetails detailsPanel, JTabbedPane tabbedPane, GameEvent event){
		super("Remove", detailsPanel, tabbedPane, event);

		this.addActionListener(actionEvent -> {
			this.event.getScenarios().remove(this.tabbedPane.getSelectedIndex());
			this.detailsPanel.refresh();
		});
		
		this.update();
	}

	@Override
	public void update(){
		//If the surviving scenario (index 0) or the required fatal scenario (index 1) is selected, disable this button
		this.setEnabled(this.tabbedPane.getSelectedIndex() > 1 && this.event.getScenarios().size() > 2);
	}
}
