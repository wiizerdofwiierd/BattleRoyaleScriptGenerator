package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import java.awt.*;

import static org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util.wrapComponent;

public class PanelEventDetails extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	int selectedTab = 0;

	public PanelEventDetails(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;
		
		this.setBorder(BorderFactory.createTitledBorder("Event details"));
		this.setLayout(new GridBagLayout());
	}
	
	public PanelManageEvents getEventsPanel(){
		return this.eventsPanel;
	}
	
	public void showDetails(GameEvent event){
		this.removeAll();
		
		if(event != null){
			GridBagConstraints c = new GridBagConstraints();
			c.fill      = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.weightx   = 1.0;
			
			//If this is an arena event, display the name field
			if(event.getContext() == EventContext.ARENA){
				
				JTextField nameField = new JTextField(event.getName());
				nameField.getDocument().addDocumentListener((SimpleDocumentListener) e -> event.setName(nameField.getText()));

				c.gridx   = 0;
				c.gridy   = 0;
				c.weighty = 0.0;
				c.anchor  = GridBagConstraints.PAGE_START;

				this.add(wrapComponent(nameField, "Event name:"), c);
			}
			
			//Scenario display panel (Contains a tabbed pane if arena event, single panel otherwise)
			PanelScenarioWrapper scenariosPanel = new PanelScenarioWrapper(this, event.getScenarios());
			scenariosPanel.setBorder(BorderFactory.createTitledBorder("Scenarios"));

			c.fill    = GridBagConstraints.BOTH;
			c.gridx   = 0;
			c.gridy  += 1;
			c.weighty = 1.0;
			
			this.add(scenariosPanel, c);
			
			//Context selection combobox
			JComboBox<EventContext> contextSelectionMenu = new JComboBox<>(EventContext.values());
			contextSelectionMenu.setSelectedItem(this.eventsPanel.getContext());
			
			if(event.getContext() == EventContext.ARENA){
				//Don't allow changing contexts if editing an arena event
				contextSelectionMenu.setEnabled(false);
			}
			else{
				//Don't allow changing context of a non-arena event to arena
				contextSelectionMenu.removeItem(EventContext.ARENA);
			}
			
			contextSelectionMenu.addActionListener(actionEvent -> {
				event.setContext((EventContext) contextSelectionMenu.getSelectedItem());
				this.eventsPanel.getEventListPanel().getEventList().update();
			});

			c.fill    = GridBagConstraints.HORIZONTAL;
			c.gridx   = 0;
			c.gridy  += 1;
			c.weighty = 0.0;
			c.anchor  = GridBagConstraints.PAGE_END;
			
			this.add(wrapComponent(contextSelectionMenu, "Event context:"), c);
		}
		
		this.updateUI();
	}
	
	public void refresh(){
		this.showDetails(this.eventsPanel.getSelectedEvent());
	}
}
