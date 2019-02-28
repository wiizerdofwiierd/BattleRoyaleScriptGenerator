package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Setting;

import javax.swing.*;

public class ButtonSettingToggle extends JCheckBox{
	
	public ButtonSettingToggle(String text, String toolTipText, Setting<Boolean> setting){
		super(text);

		this.setToolTipText(toolTipText);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addActionListener(actionListener -> setting.setValue(this.isSelected()));
		setting.addChangeListener((oldValue, newValue) -> this.setSelected(newValue));
		
		this.setSelected(setting.getValue());
	}
}
