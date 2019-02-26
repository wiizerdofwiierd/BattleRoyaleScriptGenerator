package org.wiizerdofwiierd.discord.battleroyalegenerator.event;

public class FatalGameEvent extends AbstractGameEvent{
	
	public FatalGameEvent(FatalScenario scenario, EventContext context){
		super(scenario, context);
	}
	
	@Override public FatalScenario getScenario(){
		return (FatalScenario) this.scenario;
	}
}
