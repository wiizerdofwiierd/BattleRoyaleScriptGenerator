package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class SimpleGameEvent extends AbstractGameEvent{

	public SimpleGameEvent(String name, SimpleScenario scenario, EventContext context){
		super(name, scenario, context);
	}
	
	@Override
	public SimpleScenario getScenario(){
		return (SimpleScenario) this.scenario;
	}
}
