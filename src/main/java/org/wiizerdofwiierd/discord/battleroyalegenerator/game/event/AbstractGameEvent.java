package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public abstract class AbstractGameEvent{

	protected String name;
	protected AbstractScenario scenario;
	protected EventContext context;

	public AbstractGameEvent(String name, AbstractScenario scenario, EventContext context){
		this.name = name;
		this.scenario = scenario;
		this.context = context;
	}
	
	public String getName(){
		return this.name;
	}
	
	public AbstractScenario getScenario(){
		return this.scenario;
	}
	
	public EventContext getContext(){
		return this.context;
	}
}
