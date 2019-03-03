package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;

import javax.swing.*;

public class NameTextField extends JTextField{

	public NameTextField(MemberTable parentTable){
		this.addActionListener(actionEvent -> {
			String name = this.getText();
			
			GameMember[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(GameMember m : members){
				m.setName(name);
			}

			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
