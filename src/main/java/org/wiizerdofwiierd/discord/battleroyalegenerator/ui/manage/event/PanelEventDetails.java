package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class PanelEventDetails extends JPanel{
	
	private PanelManageEvents eventsPanel;
	
	private GameEvent editing;
	
	public PanelEventDetails(PanelManageEvents eventsPanel){
		this.eventsPanel = eventsPanel;
		
		this.setBorder(BorderFactory.createTitledBorder("Event details"));
		this.setLayout(new GridBagLayout());
	}
	
	public void showDetails(GameEvent event){
		this.editing = event;
		
		this.removeAll();
		
		if(event == null) return;

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;

		JTextField nameField = new JTextField(event.getName());
		nameField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e){
				editing.setName(nameField.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e){
				editing.setName(nameField.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e){
				editing.setName(nameField.getText());
			}
		});
		c.gridx = 0;
		c.gridy = 0;
		this.add(nameField, c);

//		JButton saveButton = new JButton("Save");
//		saveButton.addActionListener(actionEvent -> {
//			eventsPanel.getListPanel().getEventList().updateUI();
//			SavedEventsHandler.getInstance().save();
//		});
//		c.gridx = 0;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LAST_LINE_END;
//		this.add(saveButton, c);
		
		this.updateUI();
	}
}
