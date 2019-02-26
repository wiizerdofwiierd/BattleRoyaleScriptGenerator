package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

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
	 * Populates the list with all the guilds the given {@link IDiscordClient} is on
	 * @param client {@link IDiscordClient} used to populate the list
	 */
	public void populate(IDiscordClient client){
		this.guildIds.clear();
		
		for(IGuild g : client.getGuilds()){
			System.out.println("Adding guild " + g.getName());
			guildIds.put(g.getLongID(), g.getName());
		}
		
		System.out.println("Successfully retrieved " + client.getGuilds().size() + " guilds.");
	}
	
	public long getGuildIdAt(int index){
		return this.guildIds.keySet().stream().collect(Collectors.toList()).get(index);
	}
	
	public IGuild getGuildAt(int index){
		return Main.getClient().getGuildByID(getGuildIdAt(index));
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
