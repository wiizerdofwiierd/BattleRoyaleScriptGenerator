package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage.event;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public interface SimpleDocumentListener extends DocumentListener{
	
	void update(DocumentEvent e);
	
	@Override
	default void insertUpdate(DocumentEvent e){
		this.update(e);
	}

	@Override
	default void removeUpdate(DocumentEvent e){
		this.update(e);
	}

	@Override
	default void changedUpdate(DocumentEvent e){
		this.update(e);
	}
}
