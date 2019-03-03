package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import javax.swing.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class GuildListModel extends AbstractListModel{

	private HashMap<Long, String> guildIds;
	
	public GuildListModel(){
		this.guildIds = new HashMap<>();
		this.guildIds.put(-1L, "Loading servers...");
	}

	/**
	 * Populates the list with all the guilds the given {@link JDA} is on
	 * @param client {@link JDA} used to populate the list
	 */
	public void populate(JDA client){
		this.guildIds.clear();
		
		for(Guild g : client.getGuilds()){
			System.out.println("Adding guild " + g.getName());
			guildIds.put(g.getIdLong(), g.getName());
		}
		
		System.out.println("Successfully retrieved " + client.getGuilds().size() + " guilds.");
	}
	
	public long getGuildIdAt(int index){
		return this.guildIds.keySet().stream().collect(Collectors.toList()).get(index);
	}
	
	public Guild getGuildAt(int index){
		return Main.getClient().getGuildById(getGuildIdAt(index));
	}
	
	@Override
	public int getSize(){
		return this.guildIds.size();
	}

	@Override
	public Object getElementAt(int i){
		return this.guildIds.values().toArray()[i];
	}
}
