package org.wiizerdofwiierd.discord.battleroyalegenerator.event;

public class SimpleGameEvent extends AbstractGameEvent{

	public SimpleGameEvent(SimpleScenario scenario, EventContext context){
		super(scenario, context);
	}
	
	@Override
	public SimpleScenario getScenario(){
		return (SimpleScenario) this.scenario;
	}
}
