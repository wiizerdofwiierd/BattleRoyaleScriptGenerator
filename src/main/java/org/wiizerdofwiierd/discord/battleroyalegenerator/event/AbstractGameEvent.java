package org.wiizerdofwiierd.discord.battleroyalegenerator.event;

public abstract class AbstractGameEvent{

	protected AbstractScenario scenario;
	protected EventContext context;

	public AbstractGameEvent(AbstractScenario scenario, EventContext context){
		this.scenario = scenario;
		this.context = context;
	}
	
	public AbstractScenario getScenario(){
		return this.scenario;
	}
	
	public EventContext getContext(){
		return this.context;
	}
}
