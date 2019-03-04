package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class EventScenario{
	
	private String text;
	private int numTributes;
	private boolean fatal;
	private KillerInfo killerInfo;
	
	public EventScenario(String text, int numTributes, boolean fatal, KillerInfo... killerInfo){
		this.text = text;
		this.numTributes = numTributes;
		this.fatal = fatal;
		
		if(killerInfo.length > 0){
			this.killerInfo = killerInfo[0];
		}
	}
	
	public String getText(){
		return this.text;
	}
	
	public int getNumTributes(){
		return this.numTributes;
	}
	
	public boolean isFatal(){
		return this.fatal;
	}
	
	public KillerInfo getKillerInfo(){
		return this.killerInfo;
	}
}
