package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.guild;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.menubar.MenuHelp;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;
import java.awt.*;

public class WindowGuildSelect extends JFrame{

	protected JList guildList;
	protected JButton button;
	
	private IDiscordClient client;
	
	public WindowGuildSelect(String title, IDiscordClient client){
		super(title);
		
		this.client = client;

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new MenuHelp());
		this.setJMenuBar(menuBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Guilds"));
		
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
			
			IGuild guild = guildList.getSelectedGuild();
			WindowUserSelect window = new WindowUserSelect("Manage Tributes", client, guild);
			
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
