package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.console;

import org.wiizerdofwiierd.discord.battleroyalegenerator.TextAreaOutputStream;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class WindowConsole extends JFrame{
	
	public WindowConsole(){
		super("Console");
		
		JTextArea console = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPane);
		
		PrintStream outputStream = new PrintStream(new TextAreaOutputStream(console));
		System.setOut(outputStream);
		System.setErr(outputStream);
		
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
	}
}
