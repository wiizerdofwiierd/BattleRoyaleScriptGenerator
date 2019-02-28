package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Gender;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.WindowGameManage;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DialogCreateMember extends JDialog{
	
	protected WindowGameManage mainWindow;
	
	public DialogCreateMember(WindowGameManage mainWindow){
		super(mainWindow, "Add custom member", true);
		this.mainWindow = mainWindow;
		
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

			this.mainWindow.getSettings().getMembers().add(member);
			this.mainWindow.getTributesPanel().updateMemberLists();
			this.mainWindow.setSelectedTab(0);
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
