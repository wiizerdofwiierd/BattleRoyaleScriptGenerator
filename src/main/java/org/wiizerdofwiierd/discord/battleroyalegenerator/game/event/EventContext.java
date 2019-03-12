package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public enum EventContext{

	BLOODBATH("Bloodbath"),
	DAY("Day"),
	NIGHT("Night"),
	FEAST("Feast"),
	ARENA("Arena");

	private String name;

	EventContext(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public String getTag(){
		return this.name.toLowerCase();
	}

	public String getTagFatal(){
		return getTag() + "fatal";
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}