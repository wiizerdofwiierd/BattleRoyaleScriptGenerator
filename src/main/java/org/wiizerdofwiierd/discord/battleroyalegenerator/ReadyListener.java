package org.wiizerdofwiierd.discord.battleroyalegenerator;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings("unused")
public final class ReadyListener extends ListenerAdapter{
	
	@Override
	public void onReady(ReadyEvent event){
		Main.getGuildSelectionWindow().update();
	}
}
