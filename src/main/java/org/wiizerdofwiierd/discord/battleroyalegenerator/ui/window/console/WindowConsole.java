package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.console;

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
		
		/*
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		System.out.println("Default Charset=" + Charset.defaultCharset());
		System.out.println("Default Charset in Use=" + getDefaultCharSet());
		*/
		
		this.setPreferredSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	/*
	private static String getDefaultCharSet() {
		OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
		String enc = writer.getEncoding();
		return enc;
	}
	*/
}
