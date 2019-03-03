package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class UpdatingMouseMotionAdapter extends MouseMotionAdapter{

	private MouseAdapterListener listener;
	
	public UpdatingMouseMotionAdapter(MouseAdapterListener listener){
		this.listener = listener;
	}

	@Override
	public void mouseMoved(MouseEvent event){
		boolean found = false;
		
		for(int i = 0;i < this.listener.getNumRows();i++){
			Rectangle bounds = this.listener.getBoundsForRow(i);
			if(bounds.contains(event.getPoint())){
				//Don't bother setting the index again if it hasn't changed
				if(this.listener.getIndex() == i){
					return;
				}
				
				this.listener.setIndex(i);
				found = true;
				break;
			}
		}

		if(!found && this.listener.getIndex() != -1){
			listener.reset();
		}
	}
}
