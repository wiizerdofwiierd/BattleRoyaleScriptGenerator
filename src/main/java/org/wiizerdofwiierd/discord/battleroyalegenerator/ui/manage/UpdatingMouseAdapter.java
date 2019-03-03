package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdatingMouseAdapter extends MouseAdapter{

	private MouseAdapterListener listener;
	
	public UpdatingMouseAdapter(MouseAdapterListener listener){
		this.listener = listener;
	}

	@Override
	public void mouseExited(MouseEvent event){
		if(this.listener.getIndex() != -1){
			this.listener.reset();
		}
	}
}
