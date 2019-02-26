package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.ScriptGenerator;
import org.wiizerdofwiierd.discord.battleroyalegenerator.web.ScriptExecutor;

import javax.swing.*;

public class ButtonGenerateScript extends JButton{
	
	protected WindowGameManage mainWindow;
	protected PanelGenerate generatePanel;
	
	public ButtonGenerateScript(WindowGameManage mainWindow, PanelGenerate generatePanel){
		super("Generate");
		this.mainWindow = mainWindow;
		this.generatePanel = generatePanel;

		this.generatePanel = generatePanel;

		Settings settings = this.mainWindow.getSettings();
		
		ScriptGenerator generator = new ScriptGenerator(
				this.mainWindow.getClient(),
				this.mainWindow.getGuild(),
				settings
		);
		
		this.addActionListener(actionEvent -> {
			this.generatePanel.toggleProgressBar();
			
			String script = generator.generateScript();
			this.generatePanel.setProgress(0.3F);
			
			if(settings.autoGenerateLink.getValue()){
				ScriptExecutor executor = new ScriptExecutor(this.generatePanel, settings);
				executor.execute(script);
			}
			else{
				this.generatePanel.setProgress(1.0F);
				
				JLabel label = new JLabel("Script successfully generated!", SwingConstants.CENTER);
				JOptionPane.showMessageDialog(
						this.mainWindow,
						label,
						"Battle Royale Script Generator v" + Main.VERSION,
						JOptionPane.PLAIN_MESSAGE
				);
			}
		});
	}
	
	public void update(){
		Settings settings = this.mainWindow.getSettings();
		MemberList members = settings.getMembers();
		
		int participating = members.getParticipatingMembers().size();
		int required = this.mainWindow.getSettings().castSize.getValue().getNumPlayers();
		
		boolean sizeMet = participating == required;
		boolean validNames = true;
		for(Member m : members.getMembersByParticipation(true, settings)){
			if(!Member.validateName(m.getName() + m.getNickname())){
				validNames = false;
				break;
			}
		}
		
		this.setEnabled(sizeMet && validNames);
	}
}
