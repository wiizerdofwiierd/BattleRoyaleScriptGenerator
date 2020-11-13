package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.WindowGameManage;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.menubar.MenuHelp;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WindowGuildSelect extends JFrame{

	protected JList guildList;
	protected JButton button;
	
	private JDA client;
	
	public WindowGuildSelect(JDA client){
		super("Select Server...");

		System.out.println("Opening guild selection window");
		
		this.client = client;

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new MenuHelp());
		this.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Servers"));
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		GuildList guildList = new GuildList(this);

		guildList.setPreferredSize(new Dimension(450, 600));
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.975;
		panel.add(guildList, c);
		this.guildList = guildList;
		
		JButton button = new JButton("Choose");
		button.setEnabled(false);
		button.addActionListener(actionEvent -> {
			WindowGuildSelect.this.setVisible(false);
			
			Guild guild = guildList.getSelectedGuild();
			WindowGameManage window = new WindowGameManage(client, guild);
			
			SwingUtilities.invokeLater(() -> window.setVisible(true));
		});
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0.025;
		panel.add(button, c);
		this.button = button;
		
		this.add(panel);
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "choose");
		panel.getActionMap().put("choose", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent actionEvent){
				button.doClick();
			}
		});
		
		
		this.setPreferredSize(Util.getMultipleOfScreenResolution(0.2F, 0.5F));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void update(){
		GuildListModel model = (GuildListModel) this.guildList.getModel();
		System.out.println("Retrieving guilds...");
		model.populate(this.client);
		this.guildList.updateUI();
		this.repaint();
	}
}
