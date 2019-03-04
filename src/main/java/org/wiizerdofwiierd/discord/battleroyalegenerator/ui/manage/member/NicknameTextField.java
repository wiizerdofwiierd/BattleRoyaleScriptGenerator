package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;

import javax.swing.*;

public class NicknameTextField extends JTextField{
	
	public NicknameTextField(MemberTable parentTable){
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addActionListener(actionEvent -> {
			String nickname = this.getText();

			GameMember[] members = parentTable.getTributesPanel().getSelectedMembers();
			for(GameMember m : members){
				m.setNickname(nickname);
			}
			
			parentTable.getTributesPanel().updateGenerateButton();
		});
	}
}
