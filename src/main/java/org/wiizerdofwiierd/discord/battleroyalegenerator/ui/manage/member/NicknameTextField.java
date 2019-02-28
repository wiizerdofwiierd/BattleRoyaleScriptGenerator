package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;

import javax.swing.*;

public class NicknameTextField extends JTextField{
	
	public NicknameTextField(MemberTable parentTable){
		this.addActionListener(actionEvent -> {
			String nickname = this.getText();

			Member[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(Member m : members){
				m.setNickname(nickname);
			}
			
			parentTable.update();
			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
