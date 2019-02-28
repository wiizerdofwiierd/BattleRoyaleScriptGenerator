package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class ArenaGameEvent extends FatalGameEvent{
	
	private String eventTitle;
	private FatalScenario[] eventScenarios;
	
	public ArenaGameEvent(String name, String eventTitle, ArenaScenario scenario, FatalScenario... eventScenarios){
		super(name, scenario, EventContext.ARENA);
		this.eventTitle = eventTitle;
		this.eventScenarios = eventScenarios;
	}
	
	public String getEventTitle(){
		return this.eventTitle;
	}
	
	public FatalScenario getEventScenario(int index){
		if(index == 0)
			return (ArenaScenario) this.scenario;
		else 
			return this.eventScenarios[index - 1];
	}
}
