package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;

public class Settings{
	
	public Setting<Boolean> showBots;
	public Setting<Boolean> showCustom;
	public Setting<Boolean> autoGenerateLink;
	public Setting<Boolean> autoCopyLink;
	public Setting<CastSize> castSize;
	
	private MemberList members;
	
	public Settings(){
		this.showBots 			= new Setting<>(false);
		this.showCustom 		= new Setting<>(true);
		this.autoGenerateLink 	= new Setting<>(true);
		this.autoCopyLink 		= new Setting<>(true);
		this.castSize 			= new Setting<>(CastSize.SIZE_24);
		
		this.members = new MemberList();
	}

	public MemberList getMembers(){
		return this.members;
	}
}
