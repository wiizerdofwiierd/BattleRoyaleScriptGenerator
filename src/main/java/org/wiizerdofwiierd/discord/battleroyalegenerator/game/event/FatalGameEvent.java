package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class FatalGameEvent extends AbstractGameEvent{
	
	public FatalGameEvent(String name, FatalScenario scenario, EventContext context){
		super(name, scenario, context);
	}
	
	@Override public FatalScenario getScenario(){
		return (FatalScenario) this.scenario;
	}
}
