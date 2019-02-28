package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;

import javax.swing.*;

public class NameTextField extends JTextField{

	public NameTextField(MemberTable parentTable){
		this.addActionListener(actionEvent -> {
			String name = this.getText();
			
			Member[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(Member m : members){
				m.setName(name);
			}
			
			parentTable.update();
			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
