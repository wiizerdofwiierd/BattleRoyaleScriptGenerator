package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.creation.WindowImgEntry;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.guild.WindowGuildSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.menubar.MenuHelp;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class MenuBarUserSelect extends JMenuBar{
	
	private WindowUserSelect parentFrame;
	
	public MenuBarUserSelect(WindowUserSelect parentFrame){
		this.parentFrame = parentFrame;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new JsonFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		Icon saveIcon = UIManager.getIcon("FileView.floppyDriveIcon");
		Icon fileIcon = UIManager.getIcon("FileView.fileIcon");
		
		//
		//File menu
		//
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem saveButton = new JMenuItem("Save", saveIcon);
		saveButton.addActionListener(actionEvent -> Main.getSettingsHandler().save());
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
			
			Main.getSettingsHandler().save(file);
		});
		fileMenu.add(saveAsButton);

		fileMenu.addSeparator();
		
		JMenuItem loadButton = new JMenuItem("Load...", fileIcon);
		loadButton.addActionListener(actionEvent -> {
			fileChooser.showOpenDialog(this);
			
			File file = fileChooser.getSelectedFile();
			if(file == null) return;
			
			Main.getSettingsHandler().load(file);
			
			this.parentFrame.setSettings(Main.getSettingsHandler().getSettingsForGuild(this.parentFrame.getGuild()));
			this.parentFrame.update();
		});
		fileMenu.add(loadButton);

		fileMenu.addSeparator();
		
		JMenuItem returnButton = new JMenuItem("Select guild...");
		returnButton.addActionListener(actionEvent -> {
			this.parentFrame.dispose();

			WindowGuildSelect guildSelect = Main.getGuildSelectionWindow();
			guildSelect.setVisible(true);
			guildSelect.toFront();
			guildSelect.requestFocus();
		});
		fileMenu.add(returnButton);
		
		this.add(fileMenu);

		//
		//Edit menu
		//
		JMenu editMenu = new JMenu("Edit");
		fileMenu.setMnemonic(KeyEvent.VK_E);

		JMenuItem customUserButton = new JMenuItem("Add custom member");
		customUserButton.addActionListener(actionEvent -> SwingUtilities.invokeLater(() -> {
			WindowImgEntry window = new WindowImgEntry(this.parentFrame);
			window.setLocationRelativeTo(this.parentFrame);
			window.setVisible(true);
		}));
		editMenu.add(customUserButton);
		
		this.add(editMenu);
		
		//
		//Help menu
		//
		MenuHelp helpMenu = new MenuHelp();
		this.add(helpMenu);
	}
}
