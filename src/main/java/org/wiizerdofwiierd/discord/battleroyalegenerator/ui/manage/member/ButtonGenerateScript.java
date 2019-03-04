package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.GameMember;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.ScriptGenerator;
import org.wiizerdofwiierd.discord.battleroyalegenerator.web.ScriptExecutor;

import javax.swing.*;

public class ButtonGenerateScript extends JButton{
	
	protected PanelManageTributes tributesPanel;
	protected PanelGenerate generatePanel;
	
	public ButtonGenerateScript(PanelManageTributes tributesPanel, PanelGenerate generatePanel){
		super("Generate");
		this.tributesPanel = tributesPanel;
		this.generatePanel = generatePanel;

		this.generatePanel = generatePanel;

		Settings settings = this.tributesPanel.getSettings();
		
		ScriptGenerator generator = new ScriptGenerator(
				this.tributesPanel.getMainWindow().getClient(),
				this.tributesPanel.getMainWindow().getGuild(),
				settings
		);
		
		this.addActionListener(actionEvent -> {
			this.generatePanel.toggleProgressBar();
			
			this.tributesPanel.getMainWindow().setStatusBarText("Generating script...");
			String script = generator.generateScript();
			this.generatePanel.setProgress(0.3F);
			
			if(settings.autoGenerateLink.getValue()){
				ScriptExecutor executor = new ScriptExecutor(this.generatePanel, settings);
				executor.execute(script);
			}
			else{
				this.generatePanel.setProgress(1.0F);
				this.tributesPanel.getMainWindow().setStatusBarText("Script saved to %s", ScriptGenerator.OUTPUT_FILE);
				
				JLabel label = new JLabel("Script successfully generated!", SwingConstants.CENTER);
				JOptionPane.showMessageDialog(
						this.tributesPanel.getMainWindow(),
						label,
						"Battle Royale Script Generator v" + Main.VERSION,
						JOptionPane.PLAIN_MESSAGE
				);
			}
		});
	}
	
	public void update(){
		Settings settings = this.tributesPanel.getSettings();
		MemberList members = settings.getMembers();
		
		int participating = members.getParticipatingMembers().size();
		int required = settings.castSize.getValue().getNumPlayers();
		
		boolean sizeMet = participating == required;
		boolean validNames = true;
		for(GameMember m : members.getMembersByParticipation(true, settings)){
			if(!GameMember.validateName(m.getName() + m.getNickname())){
				validNames = false;
				break;
			}
		}
		
		this.setEnabled(sizeMet && validNames);
	}
}
