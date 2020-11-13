package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Tribute;

import javax.swing.*;

public class NicknameTextField extends JTextField{
	
	public NicknameTextField(MemberTable parentTable){
		this.addActionListener(actionEvent -> {
			String nickname = this.getText();

			Tribute[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(Tribute m : members){
				m.setNickname(nickname);
			}
			
			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
