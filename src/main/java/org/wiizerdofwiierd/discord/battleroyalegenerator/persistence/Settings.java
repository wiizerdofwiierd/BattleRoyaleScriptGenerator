package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.TributeList;

import java.util.List;

public class Settings{
	
	public Setting<Boolean> showBots;
	public Setting<Boolean> showCustom;
	public Setting<Boolean> autoGenerateLink;
	public Setting<Boolean> autoCopyLink;
	public Setting<CastSize> castSize;
	
	private TributeList members;
	private List<String> events;
	
	public Settings(){
		this.showBots 			= new Setting<>(false);
		this.showCustom 		= new Setting<>(true);
		this.autoGenerateLink 	= new Setting<>(true);
		this.autoCopyLink 		= new Setting<>(true);
		this.castSize 			= new Setting<>(CastSize.SIZE_24);
		
		this.members = new TributeList();
	}

	public TributeList getMembers(){
		return this.members;
	}
}
