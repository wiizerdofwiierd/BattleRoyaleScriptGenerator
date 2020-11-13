package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Tribute;

import javax.swing.*;

public class NameTextField extends JTextField{

	public NameTextField(MemberTable parentTable){
		this.addActionListener(actionEvent -> {
			String name = this.getText();
			
			Tribute[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(Tribute m : members){
				m.setName(name);
			}

			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
