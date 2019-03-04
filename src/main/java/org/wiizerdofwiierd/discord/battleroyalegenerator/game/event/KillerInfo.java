package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class KillerInfo{
	
	private int[] killers;
	private int[] killed;
	
	public KillerInfo(int[] killers, int[] killed){
		this.killers = killers;
		this.killed = killed;
	}
	
	public int[] getKillers(){
		return this.killers;
	}
	
	public int[] getKilled(){
		return this.killed;
	}
}
