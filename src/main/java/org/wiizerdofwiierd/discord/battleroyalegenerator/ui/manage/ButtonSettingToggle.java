package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Setting;

import javax.swing.*;

public class ButtonSettingToggle extends JCheckBox{
	
	protected WindowGameManage mainWindow;
	
	public ButtonSettingToggle(WindowGameManage mainWindow, String text, String toolTipText, Setting<Boolean> setting){
		super(text);
		this.mainWindow = mainWindow;
		
		this.setToolTipText(toolTipText);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addActionListener(actionListener -> setting.setValue(this.isSelected()));
		setting.addChangeListener((oldValue, newValue) -> this.setSelected(newValue));
		
		this.setSelected(setting.getValue());
	}
}
