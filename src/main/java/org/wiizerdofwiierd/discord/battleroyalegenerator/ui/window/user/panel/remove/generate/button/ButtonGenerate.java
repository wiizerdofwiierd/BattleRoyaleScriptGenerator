package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.button;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.ScriptGenerator;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api.UpdatableBotComponent;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.panel.remove.generate.PanelGenerate;
import org.wiizerdofwiierd.discord.battleroyalegenerator.web.ScriptExecutor;

import javax.swing.*;

public class ButtonGenerate extends JButton implements UpdatableBotComponent{
	
	private PanelGenerate parentPanel;
	
	public ButtonGenerate(PanelGenerate generatePanel){
		super("Generate");

		this.parentPanel = generatePanel;

		Settings settings = this.parentPanel.getParentFrame().getSettings();
		
		ScriptGenerator generator = new ScriptGenerator(
				this.parentPanel.getClient(),
				this.parentPanel.getGuild(),
				settings
		);
		
		this.addActionListener(actionEvent -> {
			this.parentPanel.toggleProgressBar();
			
			String script = generator.generateScript();
			this.parentPanel.setProgress(0.3F);
			
			if(settings.autoGenerateURL()){
				ScriptExecutor executor = new ScriptExecutor(this.parentPanel);
				executor.execute(script);
			}
			else{
				this.parentPanel.setProgress(1.0F);
				
				JLabel label = new JLabel("Script successfully generated!", SwingConstants.CENTER);
				JOptionPane.showMessageDialog(
						this.parentPanel.getParentFrame(),
						label,
						"Battle Royale Script Generator v" + Main.VERSION,
						JOptionPane.PLAIN_MESSAGE
				);
			}
		});
	}
	
	public PanelGenerate getParentPanel(){
		return this.parentPanel;
	}

	@Override
	public void update(){
		WindowUserSelect mainWindow = this.getParentPanel().getParentFrame();

		Settings settings = this.parentPanel.getParentFrame().getSettings();
		MemberList members = settings.getMembers();
		
		int participating = members.getParticipatingMembers().size();
		int required = mainWindow.getSettings().getCastSize().getNumPlayers();
		
		boolean sizeMet = participating == required;
		boolean validNames = true;
		for(Member m : members.getMembersByParticipation(true, settings)){
			if(!Member.validateName(m.getName() + m.getNickname())){
				validNames = false;
				break;
			}
		}
		
		mainWindow.getRightPanel().getGeneratePanel().getButton().setEnabled(sizeMet && validNames);
	}
}
