package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedEventsHandler;
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
	
	private static final int MASK_SHORTCUT = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
	private static final int MASK_SHIFT = KeyEvent.SHIFT_DOWN_MASK;
	
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
		fileMenu.setMnemonic('F');

		JMenuItem saveButton = new JMenuItem("Save", saveIcon);
		saveButton.addActionListener(actionEvent -> {
			saveFile(null);
			SavedEventsHandler.getInstance().save();
		});
		saveButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MASK_SHORTCUT));
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
			
			saveFile(file);
		});
		saveAsButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MASK_SHORTCUT + MASK_SHIFT));
		fileMenu.add(saveAsButton);

		fileMenu.addSeparator();
		
		JMenuItem loadButton = new JMenuItem("Load...", fileIcon);
		loadButton.addActionListener(actionEvent -> {
			fileChooser.showOpenDialog(this);
			
			File file = fileChooser.getSelectedFile();
			if(file == null) return;

			loadFile(file);
		});
		loadButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, MASK_SHORTCUT));
		fileMenu.add(loadButton);

		JMenuItem reloadButton = new JMenuItem("Reload");
		reloadButton.addActionListener(actionEvent -> loadFile(null));
		reloadButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, MASK_SHORTCUT + MASK_SHIFT));
		fileMenu.add(reloadButton);

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
		editMenu.setMnemonic('E');

		JMenuItem customUserButton = new JMenuItem("Add custom member");
		customUserButton.addActionListener(actionEvent -> SwingUtilities.invokeLater(() -> {
			DialogCreateMember window = new DialogCreateMember(this.mainWindow);
			window.setLocationRelativeTo(this.mainWindow);
			window.setVisible(true);
		}));
		customUserButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
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
	
	private void saveFile(File file){
		SavedSettingsHandler handler = SavedSettingsHandler.getInstance();
		
		if(file == null){
			handler.save();
		}
		else{
			handler.save(file);
		}

		this.mainWindow.setStatusBarText("Saved file %s%n", handler.getLastModifiedFile().getAbsolutePath());
	}
	
	private void loadFile(File file){
		SavedSettingsHandler handler = SavedSettingsHandler.getInstance();
		
		//Load the specified file/reload last file
		if(file == null)
			handler.load();
		else 
			handler.load(file);

		//Set the window to use our newly loaded settings
		this.mainWindow.setSettings(handler.getSettingsForGuild(this.mainWindow.getGuild()));

		//Update the member lists to display our new set of members
		this.mainWindow.getTributesPanel().updateMemberLists();

		//Set the selected tab in the window to the Tributes tab
		this.mainWindow.setSelectedTab(0);

		//Change the status bar to show the loaded file
		this.mainWindow.setStatusBarText("Loaded file %s%n", handler.getLastModifiedFile().getAbsolutePath());
	}
}
