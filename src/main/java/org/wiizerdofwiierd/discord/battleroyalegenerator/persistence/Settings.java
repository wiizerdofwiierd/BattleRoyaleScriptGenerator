package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;

public class Settings{
	
	private boolean showBots;
	private boolean showCustom;
	private boolean showHidden;
	private boolean autoGenerateUrl;
	private boolean autoCopyLink;

	private CastSize castSize;

	private MemberList members;
	
	public Settings(){
		this.showBots = false;
		this.showCustom = true;
		this.showHidden = false;
		this.autoGenerateUrl = true;
		this.autoCopyLink = true;
		this.castSize = CastSize.SIZE_36;
		this.members = new MemberList();
	}
	
	public Settings(boolean showBots, boolean showCustom, boolean showHidden, boolean autoGenerateUrl, boolean autoCopyLink, CastSize size, MemberList members){
		this.showBots = showBots;
		this.showCustom = showCustom;
		this.showHidden = showHidden;
		this.autoGenerateUrl = autoGenerateUrl;
		this.autoCopyLink = autoCopyLink;
		this.castSize = size;
		this.members = members;
	}
	
	public boolean showBots(){
		return this.showBots;
	}
	
	public boolean showCustom(){
		return this.showCustom;
	}
	
	public boolean showHidden(){
		return this.showHidden;
	}
	
	public boolean autoGenerateURL(){
		return this.autoGenerateUrl;
	}
	
	public boolean autoCopyLink(){
		return this.autoCopyLink;
	}
	
	public CastSize getCastSize(){
		return this.castSize;
	}
	
	public MemberList getMembers(){
		return this.members;
	}
	
	public void setShowBots(boolean value){
		this.showBots = value;
	}
	
	public void setShowCustom(boolean value){
		this.showCustom = value;
	}
	
	public void setShowHidden(boolean value){
		this.showHidden = value;
	}
	
	public void setAutoGenerateUrl(boolean value){
		this.autoGenerateUrl = value;
	}
	
	public void setAutoCopyLink(boolean value){
		this.autoCopyLink = value;
	}
	
	public void setCastSize(CastSize size){
		this.castSize = size;
	}
}
