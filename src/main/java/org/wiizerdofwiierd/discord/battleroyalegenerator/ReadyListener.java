package org.wiizerdofwiierd.discord.battleroyalegenerator;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class ReadyListener extends ListenerAdapter{
	
	@Override
	public void onReady(@NotNull ReadyEvent event){
		System.out.println("Ready");
		Main.getGuildSelectionWindow().update();
	}
}
