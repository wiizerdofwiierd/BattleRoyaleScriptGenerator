package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.guild.WindowGuildSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member.DialogCreateMember;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member.JsonFileFilter;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.menubar.MenuHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class MenuBarGameManage extends JMenuBar{
	
	private WindowGameManage mainWindow;
	
	public MenuBarGameManage(WindowGameManage mainWindow){
		this.mainWindow = mainWindow;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new JsonFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		Icon saveIcon = UIManager.getIcon("FileView.floppyDriveIcon");
		Icon fileIcon = UIManager.getIcon("FileView.fileIcon");
		
		//------------
		//File menu
		//------------
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem saveButton = new JMenuItem("Save", saveIcon);
		saveButton.addActionListener(actionEvent -> SavedSettingsHandler.getInstance().save());
		fileMenu.add(saveButton);
		
		JMenuItem saveAsButton = new JMenuItem("Save as...");
		saveAsButton.addActionListener(actionEvent -> {
			fileChooser.showSaveDialog(this);
			
			File file = fileChooser.getSelectedFile();
			if(file == null) return;
			
			//If the file being saved does not have the .json extension, add it
			if(!file.getPath().endsWith(".json")){
				file = new File(file + ".json");
			}

			SavedSettingsHandler.getInstance().save(file);
		});
		fileMenu.add(saveAsButton);

		fileMenu.addSeparator();
		
		JMenuItem loadButton = new JMenuItem("Load...", fileIcon);
		loadButton.addActionListener(actionEvent -> {
			fileChooser.showOpenDialog(this);
			
			File file = fileChooser.getSelectedFile();
			if(file == null) return;

			SavedSettingsHandler.getInstance().load(file);
			
			//Set the window to use our newly loaded settings
			this.mainWindow.setSettings(SavedSettingsHandler.getInstance().getSettingsForGuild(this.mainWindow.getGuild()));
			
			//Update the member lists to display our new set of members
			this.mainWindow.getTributesPanel().updateMemberLists();
			
			//Set the selected tab in the window to the Tributes tab
			this.mainWindow.setSelectedTab(0);
		});
		fileMenu.add(loadButton);

		fileMenu.addSeparator();
		
		JMenuItem returnButton = new JMenuItem("Select guild...");
		returnButton.addActionListener(actionEvent -> {
			SavedSettingsHandler.getInstance().load(SavedSettingsHandler.SETTINGS_FILE);
			this.mainWindow.dispose();

			WindowGuildSelect guildSelect = Main.getGuildSelectionWindow();
			guildSelect.setVisible(true);
			guildSelect.toFront();
			guildSelect.requestFocus();
		});
		fileMenu.add(returnButton);
		
		this.add(fileMenu);

		//------------
		//Edit menu
		//------------
		JMenu editMenu = new JMenu("Edit");
		fileMenu.setMnemonic(KeyEvent.VK_E);

		JMenuItem customUserButton = new JMenuItem("Add custom member");
		customUserButton.addActionListener(actionEvent -> SwingUtilities.invokeLater(() -> {
			DialogCreateMember window = new DialogCreateMember(this.mainWindow);
			window.setLocationRelativeTo(this.mainWindow);
			window.setVisible(true);
		}));
		editMenu.add(customUserButton);
		
		this.add(editMenu);
		
		//------------
		//Help menu
		//------------
		MenuHelp helpMenu = new MenuHelp();
		this.add(helpMenu);
		
		//Sets left and right margins for every JMenu in this JMenuBar
		for(int i = 0;i < this.getMenuCount();i++){
			JMenu m = this.getMenu(i);
			m.setMargin(new Insets(0, 2, 0, 2));
		}
	}
}
