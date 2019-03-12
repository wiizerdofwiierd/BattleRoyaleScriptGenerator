package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelScenarioWrapper extends JPanel{
	
	//TODO: Create panel in update method rather than constructor
	public PanelScenarioWrapper(PanelEventDetails detailsPanel, List<EventScenario> scenarios){
		if(scenarios == null || scenarios.size() == 0) return;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridwidth = 2;

		GameEvent event = detailsPanel.getEventsPanel().getSelectedEvent();

		//If this is an arena event, add a tabbed pane to display the multiple scenarios
		if(detailsPanel.getEventsPanel().getContext() == EventContext.ARENA){
			JTabbedPane tabbedPane = new JTabbedPane();
			
			for(int i = 0;i < scenarios.size();i++){
				EventScenario s = scenarios.get(i);
				boolean isArenaSurvivingScenario = i == 0;

				String title;
				if(isArenaSurvivingScenario)
					title = "Surviving";
				else
					title = "Fatal " + i;
				
				tabbedPane.add(title, new PanelScenarioInfo(detailsPanel, s, isArenaSurvivingScenario));
			}
			
			//Save selected tab for restoration when this tab is re-created
			tabbedPane.addChangeListener(e -> detailsPanel.selectedTab = tabbedPane.getSelectedIndex());
			
			//Set selected tab to previously saved value (or the last tab if the previous tab no longer exists)
			tabbedPane.setSelectedIndex(detailsPanel.selectedTab > tabbedPane.getTabCount() - 1 ? tabbedPane.getTabCount() - 1 : detailsPanel.selectedTab);

			this.add(tabbedPane, c);

			c.weighty = 0.0;
			c.gridwidth = 1;
			
			//Button to add another scenario to an arena event
			ButtonScenarioAdd buttonScenarioAdd = new ButtonScenarioAdd(detailsPanel, tabbedPane, event);
			
			c.gridx = 0;
			c.gridy = 1;
			
			this.add(buttonScenarioAdd, c);

			//Button to remove the scenario for the currently selected tab
			ButtonScenarioRemove buttonScenarioRemove = new ButtonScenarioRemove(detailsPanel, tabbedPane, event);
			tabbedPane.addChangeListener(e -> buttonScenarioRemove.update());
			
			c.gridx = 1;
			c.gridy = 1;
			c.anchor = GridBagConstraints.PAGE_END;
			
			this.add(buttonScenarioRemove, c);
		}
		//This is not an arena event and there is only one scenario, add it as its own panel
		else{
			this.add(new PanelScenarioInfo(detailsPanel, scenarios.get(0), false), c);
		}
	}
}
