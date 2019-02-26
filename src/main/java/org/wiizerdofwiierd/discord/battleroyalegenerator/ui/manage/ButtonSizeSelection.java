package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;

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
