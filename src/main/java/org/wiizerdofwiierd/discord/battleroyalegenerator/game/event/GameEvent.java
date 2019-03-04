package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class GameEvent{
	
	private String name;
	private EventContext context;
	private EventScenario[] scenarios;
	
	public GameEvent(String name, EventContext context, EventScenario... scenarios){
		this.name = name;
		this.context = context;
		this.scenarios = scenarios;
	}
	
	public String getName(){
		return this.name;
	}
	
	public EventContext getContext(){
		return this.context;
	}
	
	public EventScenario[] getScenarios(){
		return this.scenarios;
	}
	
	public boolean isFatal(){
		for(EventScenario s : this.scenarios){
			if(s.isFatal()) return true;
		}
		
		return false;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setScenarios(EventScenario[] scenarios){
		this.scenarios = scenarios;
	}
}
