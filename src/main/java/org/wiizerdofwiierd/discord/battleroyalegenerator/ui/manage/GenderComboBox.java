package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Gender;

import javax.swing.*;

public class GenderComboBox extends JComboBox{
	
	private MemberTable parentTable;
	
	public GenderComboBox(MemberTable parentTable){
		this.parentTable = parentTable;
		
		((JLabel) this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		for(Gender g : Gender.values()){
			this.addItem(g.toString());
		}
	}
}
