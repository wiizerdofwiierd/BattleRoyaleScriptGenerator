package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

public class PanelListServer extends AbstractListPanel{
	
	public PanelListServer(WindowGameManage parentFrame){
		super(parentFrame, "Members in " + parentFrame.getGuild().getName(), false);
	}
	
	@Override
	public void update(){
		super.update();
	}
}
