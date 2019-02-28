package org.wiizerdofwiierd.discord.battleroyalegenerator;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

@SuppressWarnings("unused")
public final class EventListener{
	
	@EventSubscriber
	public void onReady(ReadyEvent event){
		Main.getGuildSelectionWindow().update();
	}
}
