package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameEvent{
	
	public static final int MAX_SCENARIOS = 6;
	
	private String name;
	private EventContext context;
	private List<EventScenario> scenarios;
	
	public GameEvent(String name, EventContext context, EventScenario... scenarios){
		this.name = name;
		this.context = context;
		
		this.scenarios = new LinkedList<>(Arrays.asList(scenarios));
	}
	
	public String getName(){
		return this.name;
	}
	
	public EventContext getContext(){
		return this.context;
	}
	
	public List<EventScenario> getScenarios(){
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
	
	public void setContext(EventContext context){
		this.context = context;
	}
}
