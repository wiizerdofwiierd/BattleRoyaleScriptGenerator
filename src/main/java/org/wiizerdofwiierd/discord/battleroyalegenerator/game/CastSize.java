package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

public enum CastSize{
	
	SIZE_24(24),
	SIZE_36(36),
	SIZE_48(48);
	
	private int numPlayers;
	
	CastSize(int numPlayers){
		this.numPlayers = numPlayers;
	}
	
	public static CastSize getFromValue(int value){
		for(CastSize s : values()){
			if(s.numPlayers == value){
				return s;
			}
		}
		
		return null;
	}
	
	public int getNumPlayers(){
		return this.numPlayers;
	}
}
