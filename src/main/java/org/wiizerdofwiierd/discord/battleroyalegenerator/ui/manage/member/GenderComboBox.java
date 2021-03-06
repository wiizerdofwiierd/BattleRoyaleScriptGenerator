package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Gender;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;

import javax.swing.*;

public class GenderComboBox extends JComboBox{
	
	public GenderComboBox(MemberTable parentTable){
		((JLabel) this.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		for(Gender g : Gender.values()){
			this.addItem(g);
		}

		this.addActionListener(actionEvent -> {
			GameMember[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(GameMember m : members){
				m.setGender((Gender) this.getSelectedItem());
			}
		});
	}
}
