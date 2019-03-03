package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import java.awt.*;

public interface MouseAdapterListener{
	
	Rectangle getBoundsForRow(int row);
	
	int getNumRows();
	
	int getIndex();
	
	void setIndex(int index);
	
	void reset();
}
