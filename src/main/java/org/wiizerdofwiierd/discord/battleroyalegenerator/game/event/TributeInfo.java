package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class TributeInfo{
	
	private boolean isKiller;
	private boolean isKilled;
	
	public TributeInfo(boolean isKiller, boolean isKilled){
		this.isKiller = isKiller;
		this.isKilled = isKilled;
	}
	
	public boolean isKiller(){
		return this.isKiller;
	}
	
	public boolean isKilled(){
		return this.isKilled;
	}
}
