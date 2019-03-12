package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public final class GameEventFactory{
	
	public static GameEvent createEvent(EventContext context){
		if(context == EventContext.ARENA){
			return new GameEvent("New Arena Event", context, new EventScenario(), new EventScenario());
		}
		else{
			return new GameEvent(null, context, new EventScenario());
		}
	}
}
