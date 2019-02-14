package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.creation;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Gender;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class WindowImgEntry extends JDialog{
	
	private WindowUserSelect parentFrame;
	
	public WindowImgEntry(WindowUserSelect parentFrame){
		super(parentFrame, "Add custom user", true);
		this.parentFrame = parentFrame;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		JLabel urlInputLabel = new JLabel("Image URL:", SwingConstants.CENTER);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		this.add(urlInputLabel, c);
		
		JTextArea urlInput = new JTextArea();
		urlInput.setFont(urlInputLabel.getFont());
		urlInput.getDocument().putProperty("filterNewlines", true);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.9;
		c.insets = new Insets(0, 16, 0, 16);
		JScrollPane scrollPane = new JScrollPane(urlInput, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane, c);
		
		JButton addButton = new JButton("Create");
		addButton.addActionListener(actionEvent -> {
			Member member = Member.createCustomMember(
					Gender.values()[new Random().nextInt(Gender.values().length)],
					"Custom Member",
					"Custom Member",
					urlInput.getText());

			this.parentFrame.getSettings().getMembers().add(member);
			this.parentFrame.update();
			this.dispose();
		});
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.weightx = 1.0;
		addButton.setPreferredSize(new Dimension(150, 25));
		this.add(addButton, c);

		
		this.setResizable(false);
		this.setPreferredSize(new Dimension(800, 120));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
	}
}
