package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import javax.swing.*;
import java.awt.*;

public class SubtleScrollPane extends JScrollPane{
	
	public SubtleScrollPane(Component view){
		this(view, false);
	}

	public SubtleScrollPane(Component view, boolean horizontalScrolling){
		super(view, VERTICAL_SCROLLBAR_AS_NEEDED, horizontalScrolling ? HORIZONTAL_SCROLLBAR_AS_NEEDED : HORIZONTAL_SCROLLBAR_NEVER);
		this.setBorder(BorderFactory.createEmptyBorder());
	}
}
