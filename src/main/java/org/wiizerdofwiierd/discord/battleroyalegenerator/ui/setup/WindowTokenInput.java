package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.setup;

import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowTokenInput extends JDialog{
	
	private String token;
	
	public WindowTokenInput(){
		super(null, "Setup", ModalityType.APPLICATION_MODAL);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		
		JLabel tokenLabel = new JLabel("Enter your bot's token: ", SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		this.add(tokenLabel, c);
		
		
		JTextArea tokenInput = new JTextArea();
		tokenInput.getDocument().putProperty("filterNewlines", true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.insets = new Insets(0, 0, 0, 20);
		
		JScrollPane scrollPane = new JScrollPane(tokenInput, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane, c);
		
		
		JButton buttonContinue = new JButton("Continue");
		buttonContinue.setEnabled(false);
		
		buttonContinue.addActionListener(actionEvent -> {
			this.token = tokenInput.getText();
			this.dispose();
		});
		
		tokenInput.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent keyEvent){
				buttonContinue.setEnabled(!tokenInput.getText().isEmpty());
			}
		});
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 1.0;
		this.add(buttonContinue, c);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.4F, 0.1F));
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public String getToken(){
		return this.token;
	}
}
