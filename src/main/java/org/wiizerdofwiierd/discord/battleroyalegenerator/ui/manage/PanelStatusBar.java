package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.manage;

import javax.swing.*;
import java.awt.*;

public class PanelStatusBar extends JPanel{
	
	private static final int HEIGHT = 16;
	
	private JLabel label;
	
	public PanelStatusBar(){
		this("");
	}
	
	public PanelStatusBar(String defaultStatus, Object... values){
		this.label = new JLabel();
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		
		this.setStatus(defaultStatus, values);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 4, 0, 0);
		this.add(label, c);
		
		this.setPreferredSize(new Dimension(0, HEIGHT));
		this.setMinimumSize(new Dimension(0, HEIGHT));
	}
	
	public void setStatus(String text, Object... values){
		setStatus(String.format(text, values));
	}
	
	public void setStatus(String text){
		this.label.setText(text);
	}
}
