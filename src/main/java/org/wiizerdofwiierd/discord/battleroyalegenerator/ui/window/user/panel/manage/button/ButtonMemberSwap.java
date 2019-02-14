package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.manage.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;

import javax.swing.*;

public class ButtonMemberSwap extends JButton{

	private WindowUserSelect parentFrame;
	private boolean participation;

	public ButtonMemberSwap(WindowUserSelect parentFrame, String title, boolean participationToSet){
		super(title);

		this.parentFrame = parentFrame;
		this.participation = participationToSet;

		this.addActionListener(actionEvent -> {
			System.out.println("Setting " + this.parentFrame.getSelectedMembers().length + " members to participation = " + this.participation);
			
			for(Member m : this.parentFrame.getSelectedMembers()){
				m.setParticipating(this.participation);
			}

			this.parentFrame.update();
		});
	}
}
