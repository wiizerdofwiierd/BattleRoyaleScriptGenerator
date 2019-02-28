package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public abstract class AbstractScenario{

	protected int numTributes;
	protected String eventText;
	protected boolean isFatal;

	public AbstractScenario(int numTributes, String eventText, boolean isFatal){
		this.numTributes = numTributes;
		this.eventText = eventText;
	}

	public int getNumTributes(){
		return this.numTributes;
	}

	public String getEventText(){
		return this.eventText;
	}
	
	public boolean isFatal(){
		return this.isFatal;
	}
}
