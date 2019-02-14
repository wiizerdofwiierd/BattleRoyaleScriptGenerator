package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.AbstractBotPanel;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button.ButtonGenerate;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button.ButtonSizeSelection;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button.ScriptProgressBar;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.settings.PanelGenerationSettings;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PanelGenerate extends AbstractBotPanel{
	
	private ButtonGroup sizeButtons;
	
	private boolean showingButton = true;
	
	private ScriptProgressBar progressBar;
	private ButtonGenerate generateButton;
	
	public PanelGenerate(AbstractBotPanel parent){
		super(parent);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		this.sizeButtons = new ButtonGroup();
	
		int index = 0;
		for(CastSize s : CastSize.values()){
			ButtonSizeSelection button = new ButtonSizeSelection(this, s);
			button.addActionListener(actionEvent -> {
				this.parentFrame.getSettings().setCastSize(s);
				this.parentFrame.update();
			});

			//Select the button that corresponds with the loaded Settings
			if(this.getParentFrame().getSettings().getCastSize() == s){
				button.setSelected(true);
			}

			c.gridx = index;

			sizeButtons.add(button);
			this.add(button, c);

			index++;
		}
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = this.sizeButtons.getButtonCount();
		c.ipady = 20;
		c.insets = new Insets(10, 0, 0, 0);
		c.anchor = GridBagConstraints.PAGE_END;
		
		this.progressBar = new ScriptProgressBar(this);
		this.add(progressBar, c);
		
		this.generateButton = new ButtonGenerate(this);
		this.add(generateButton, c);

		
		PanelGenerationSettings settingsPanel = new PanelGenerationSettings(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = this.sizeButtons.getButtonCount();
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		this.add(settingsPanel, c);
	}
	
	public ButtonGenerate getButton(){
		return this.generateButton;
	}

	public void toggleProgressBar(){
		this.generateButton.setVisible(!this.showingButton);
		this.progressBar.setVisible(this.showingButton);
		
		this.showingButton = !this.showingButton;
	}
	
	public void setProgress(float progress){
		this.progressBar.setValue((int) (100 * progress));
	}
	
	@Override
	public void update(){
		Enumeration<AbstractButton> buttons = this.sizeButtons.getElements();
		ButtonSizeSelection b;
		while(buttons.hasMoreElements()){
			b = (ButtonSizeSelection) buttons.nextElement();
			
			if(b.getCastSize() == this.parentFrame.getSettings().getCastSize()){
				b.setSelected(true);
				break;
			}
		}
		
		this.generateButton.update();
	}
}
