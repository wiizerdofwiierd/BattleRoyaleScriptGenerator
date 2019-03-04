package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public interface RowHoverListener{
	
	default void registerHoverAdapters(){
		if(!(this instanceof  Component)) return;
		
		((Component) this).addMouseListener(new UpdatingMouseAdapter(this));
		((Component) this).addMouseMotionListener(new UpdatingMouseMotionAdapter(this));
	}
	
	Rectangle getBoundsForRow(int row);
	
	int getNumRows();
	
	int getIndex();
	
	void setIndex(int index);
	
	void reset();

	class UpdatingMouseAdapter extends MouseAdapter{

		private RowHoverListener listener;

		private UpdatingMouseAdapter(RowHoverListener listener){
			this.listener = listener;
		}

		@Override
		public void mouseExited(MouseEvent event){
			if(this.listener.getIndex() != -1){
				this.listener.reset();
			}
		}
	}

	class UpdatingMouseMotionAdapter extends MouseMotionAdapter{

		private RowHoverListener listener;

		private UpdatingMouseMotionAdapter(RowHoverListener listener){
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
}
