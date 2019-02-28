package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.member;

public class PanelListServer extends AbstractMemberListPanel{
	
	public PanelListServer(PanelManageTributes tributesPanel){
		super(tributesPanel, "Members in " + tributesPanel.getGuild().getName(), false);
	}
	
	@Override
	public void update(){
		super.update();
	}
}
