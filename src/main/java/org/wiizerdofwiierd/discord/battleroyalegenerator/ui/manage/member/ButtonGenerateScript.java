package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.Tribute;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.TributeList;
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
		TributeList members = settings.getMembers();
		
		int participating = members.getParticipatingMembers().size();
		int required = settings.castSize.getValue().getNumPlayers();
		
		boolean sizeMet = participating == required;
		boolean validNames = true;
		for(Tribute m : members.getMembersByParticipation(true, settings)){
			if(!Tribute.validateName(m.getName() + m.getNickname())){
				validNames = false;
				break;
			}
		}
		
		this.setEnabled(sizeMet && validNames);
	}
}
