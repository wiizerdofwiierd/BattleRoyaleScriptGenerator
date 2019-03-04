package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.CastSize;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PanelGenerate extends JPanel{
	
	private PanelManageTributes tributesPanel;
	
	private PanelListSelected removePanel;

	private ScriptProgressBar progressBar;
	private ButtonGenerateScript generateButton;
	
	private ButtonGroup sizeButtons;
	
	private boolean showingButton = true;
	
	public PanelGenerate(PanelManageTributes tributesPanel, PanelListSelected removePanel){
		this.tributesPanel = tributesPanel;
		this.removePanel = removePanel;
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		this.sizeButtons = new ButtonGroup();
	
		int index = 0;
		for(CastSize s : CastSize.values()){
			ButtonSizeSelection button = new ButtonSizeSelection(s);
			button.addActionListener(actionEvent -> {
				this.tributesPanel.getSettings().castSize.setValue(s);
				
				this.generateButton.update();
				this.removePanel.update();
			});

			//Select the button that corresponds with the loaded Settings
			if(this.tributesPanel.getSettings().castSize.getValue() == s){
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
		
		this.generateButton = new ButtonGenerateScript(this.tributesPanel, this);
		this.add(generateButton, c);

		
		PanelGenerationSettings settingsPanel = new PanelGenerationSettings(this.tributesPanel);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = this.sizeButtons.getButtonCount();
		c.ipady = 0;
		c.insets = new Insets(0, 0, 0, 0);
		this.add(settingsPanel, c);
	}
	
	public PanelManageTributes getTributesPanel(){
		return this.tributesPanel;
	}
	
	public ButtonGenerateScript getButton(){
		return this.generateButton;
	}

	public void toggleProgressBar(){
		this.generateButton.setVisible(!this.showingButton);
		this.progressBar.setVisible(this.showingButton);
		
		this.tributesPanel.getMainWindow().setEnabled(!this.showingButton);
		
		this.showingButton = !this.showingButton;
	}
	
	public void setProgress(float progress){
		this.progressBar.setValue((int) (100 * progress));
	}
	
	public void update(){
		Enumeration<AbstractButton> buttons = this.sizeButtons.getElements();
		ButtonSizeSelection b;
		while(buttons.hasMoreElements()){
			b = (ButtonSizeSelection) buttons.nextElement();
			
			if(b.getCastSize() == this.tributesPanel.getSettings().castSize.getValue()){
				b.setSelected(true);
				break;
			}
		}
		
		this.generateButton.update();
	}
}
