package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.SubtleScrollPane;

import javax.swing.*;
import java.awt.*;

import static org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util.wrapComponent;

public class PanelScenarioInfo extends JPanel{
	
	public PanelScenarioInfo(PanelEventDetails detailsPanel, EventScenario scenario, boolean isArenaSurvivingScenario){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill    = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		
		//Top panel containing scenario text field and tribute count combobox
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		
		//Text for this scenario
		JTextField scenarioText = new JTextField(scenario.getText());
		scenarioText.getDocument().addDocumentListener((SimpleDocumentListener) e -> scenario.setText(scenarioText.getText()));

		topPanel.add(wrapComponent(scenarioText, "Scenario Text:"));

		
		//Values used to fill the combobox
		Integer[] values = new Integer[EventScenario.MAX_TRIBUTES];
		for(int i = 0;i < EventScenario.MAX_TRIBUTES;i++){
			values[i] = i + 1;
		}

		//Combobox for selecting tribute count
		JComboBox<Integer> tributeCount = new JComboBox<>(values);
		tributeCount.setSelectedItem(scenario.getNumTributes());

		//If this is the surviving scenario for an arena event, lock tributes to 1
		tributeCount.setEnabled(!isArenaSurvivingScenario);
		
		tributeCount.addActionListener(actionEvent -> {
			scenario.setNumTributes((int) tributeCount.getSelectedItem());

			//Refresh details panel to show killer info for each tribute
			detailsPanel.refresh();
		});

		topPanel.add(wrapComponent(tributeCount, "Number of Tributes:"));
		
		c.gridx  = 0;
		c.gridy  = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		
		this.add(topPanel, c);

		//Arena surviving scenario is not fatal, don't killer info panel
		if(isArenaSurvivingScenario) return;
		
		
		PanelKillerInfo killerInfo = new PanelKillerInfo(scenario);
		
		c.gridx  = 0;
		c.gridy  = 1;
		
		this.add(new SubtleScrollPane(killerInfo, true), c);
	}
}
