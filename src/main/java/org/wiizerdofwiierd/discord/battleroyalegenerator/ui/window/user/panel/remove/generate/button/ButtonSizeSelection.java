package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;

import javax.swing.*;

public class ButtonSizeSelection extends JRadioButton{
	
	private PanelGenerate generatePanel;
	private CastSize castSize;
	
	public ButtonSizeSelection(PanelGenerate generatePanel, CastSize castSize){
		super(castSize.getNumPlayers() + " tributes");
		this.generatePanel = generatePanel;
		this.castSize = castSize;
	}

	public PanelGenerate getGeneratePanel(){
		return this.generatePanel;
	}
	
	public CastSize getCastSize(){
		return castSize;
	}
}
