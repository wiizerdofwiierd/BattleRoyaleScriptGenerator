package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table.column;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.table.MemberTable;

import javax.swing.*;

public class NameTextField extends JTextField{
	
	private MemberTable parentTable;
	
	public NameTextField(MemberTable parentTable){
		this.parentTable = parentTable;
		
		this.addActionListener(actionEvent -> {
			String name = this.getText();

			Member[] members = this.parentTable.getParentFrame().getSelectedMembers();
			for(Member m : members){
				m.setName(name);
			}
			
			this.parentTable.update();
			this.parentTable.getParentFrame().updateGenerateButton();
		});
	}
}
