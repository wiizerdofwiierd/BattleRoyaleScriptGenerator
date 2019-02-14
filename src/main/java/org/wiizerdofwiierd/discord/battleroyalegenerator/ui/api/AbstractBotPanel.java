package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.api;

import org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user.WindowUserSelect;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;

import javax.swing.*;

public abstract class AbstractBotPanel extends JPanel implements UpdatableBotComponent{
	
	protected WindowUserSelect parentFrame;
	
	public AbstractBotPanel(AbstractBotPanel parent){
		this.parentFrame = parent.parentFrame;
	}
	
	public AbstractBotPanel(WindowUserSelect parentFrame){
		this.parentFrame = parentFrame;
	}
	
	public WindowUserSelect getParentFrame(){
		return this.parentFrame;
	}
	
	public IGuild getGuild(){
		return this.parentFrame.getClient().getGuildByID(this.parentFrame.getGuild().getLongID());
	}
	
	public IDiscordClient getClient(){
		return this.parentFrame.getClient();
	}
}
