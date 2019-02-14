package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table.column;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Gender;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table.MemberTable;

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
