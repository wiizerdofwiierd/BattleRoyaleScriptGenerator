package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;

import javax.swing.*;

public class ScriptProgressBar extends JProgressBar{
	
	private PanelGenerate generatePanel;
	
	public ScriptProgressBar(PanelGenerate generatePanel){
		super(0, 100);
		
		this.generatePanel = generatePanel;
		this.setVisible(false);
	}
	
	@Override
	public void setValue(int value){
		super.setValue(value);
		
		if(value == this.getMaximum()){
			this.generatePanel.toggleProgressBar();
		}
	}
}
