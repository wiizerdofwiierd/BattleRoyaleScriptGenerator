package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;

import javax.swing.*;

public class NicknameTextField extends JTextField{
	
	private MemberTable parentTable;
	
	public NicknameTextField(MemberTable parentTable){
		this.parentTable = parentTable;
		
		this.addActionListener(actionEvent -> {
			String nickname = this.getText();

			Member[] members = this.parentTable.getParentFrame().getSelectedMembers();
			for(Member m : members){
				m.setNickname(nickname);
			}
			
			this.parentTable.update();
			this.parentTable.getParentFrame().updateGenerateButton();
		});
	}
}
