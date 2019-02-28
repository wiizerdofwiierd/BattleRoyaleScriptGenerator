package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.CastSize;

import javax.swing.*;

public class ButtonSizeSelection extends JRadioButton{
	
	private CastSize castSize;
	
	public ButtonSizeSelection(CastSize castSize){
		super(castSize.getNumPlayers() + " tributes");
		this.castSize = castSize;
	}
	
	public CastSize getCastSize(){
		return castSize;
	}
}
