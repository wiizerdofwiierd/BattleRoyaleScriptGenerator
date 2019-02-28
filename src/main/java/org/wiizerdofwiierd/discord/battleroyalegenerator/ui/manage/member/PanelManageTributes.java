package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.WindowGameManage;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;
import java.awt.*;

public class PanelManageTributes extends JPanel{

	private WindowGameManage mainWindow;

	private PanelListServer listServer;
	private PanelListManage buttonPanel;
	private PanelListSelected listSelected;
	
	private Member[] selectedMembers = new Member[0];
	
	public PanelManageTributes(WindowGameManage mainWindow){
		this.mainWindow = mainWindow;
		
		this.setBorder(BorderFactory.createTitledBorder("Select Members"));
		this.setLayout(new GridLayout(1, 3));

		//Left panel (Lists members in server)
		this.listServer = new PanelListServer(this);
		this.add(listServer);

		//Center panel (Contains member control buttons)
		this.buttonPanel = new PanelListManage(this);
		this.add(buttonPanel);

		//Right panel (Contains members selected for the game)
		this.listSelected = new PanelListSelected(this);
		this.add(listSelected);
		
		//Add listeners to update the lists when settings are changed
		this.mainWindow.getSettings().showBots.addChangeListener(((oldValue, newValue) -> this.updateMemberLists()));
		this.mainWindow.getSettings().showCustom.addChangeListener(((oldValue, newValue) -> this.updateMemberLists()));

		//Update the member lists
		this.updateMemberLists();
	}
	
	public WindowGameManage getMainWindow(){
		return this.mainWindow;
	}
	
	public IGuild getGuild(){
		return this.mainWindow.getGuild();
	}
	
	public Settings getSettings(){
		return this.mainWindow.getSettings();
	}

	public MemberList getMembers(){
		return this.mainWindow.getMembers();
	}

	public Member[] getSelectedMembers(){
		return this.selectedMembers;
	}
	

	public void setSelectedMembers(Member[] selectedMembers){
		this.selectedMembers = selectedMembers;
	}

	public void updateGenerateButton(){
		this.listSelected.getGeneratePanel().getButton().update();
	}

	public void updateMemberLists(){
		this.listServer.update();
		this.listSelected.update();
	}

	public void updateButtonPanel(){
		this.buttonPanel.update();
	}
}
