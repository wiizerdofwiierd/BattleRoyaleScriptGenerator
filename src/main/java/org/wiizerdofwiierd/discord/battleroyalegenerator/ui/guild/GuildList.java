package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild;

import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class GuildList extends JList{
	
	private WindowGuildSelect mainWindow;
	private GuildListModel model;
	
	public GuildList(WindowGuildSelect mainWindow){
		GuildListModel model = new GuildListModel();
		this.model = model;
		this.setModel(model);
		
		this.mainWindow = mainWindow;
		
		this.addListSelectionListener(listSelectionEvent -> {
			if(getSelectedIndex() == -1){
				this.mainWindow.button.setEnabled(false);
			}
			else{
				this.mainWindow.button.setEnabled(true);
			}
		});
		
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent mouseEvent){
				if(mouseEvent.getClickCount() == 2){
					mainWindow.button.doClick();
				}
			}
		});
	}
	
	public WindowGuildSelect getMainWindow(){
		return this.mainWindow;
	}
	
	public IGuild getSelectedGuild(){
		return this.model.getGuildAt(getSelectedIndex());
	}
}
