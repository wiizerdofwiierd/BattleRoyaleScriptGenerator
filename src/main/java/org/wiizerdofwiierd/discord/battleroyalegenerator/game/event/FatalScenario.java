package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

public class FatalScenario extends AbstractScenario{
	
	private TributeInfo[] killerInfo;
	
	public FatalScenario(int numTributes, String eventText, TributeInfo... killerInfo){
		super(numTributes, eventText, true);
		this.killerInfo = killerInfo;
	}
	
	public TributeInfo getKillerInfo(int tributeNum){
		return this.killerInfo[tributeNum];
	}
}
