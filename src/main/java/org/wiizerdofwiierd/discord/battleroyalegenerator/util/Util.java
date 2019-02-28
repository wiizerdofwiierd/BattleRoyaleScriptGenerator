package org.wiizerdofwiierd.discord.battleroyalegenerator.util;

import org.wiizerdofwiierd.discord.battleroyalegenerator.Main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class Util{

	public static InputStreamReader getReaderForResource(String path){
		if(!path.startsWith("/")){
			path = "/" + path;
		}
		
		return new InputStreamReader(Main.class.getResourceAsStream(path));
	}
	
	public static Dimension getMultipleOfScreenResolution(float multX, float multY){
		Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension((int) (screenResolution.getWidth() * multX), (int) (screenResolution.getHeight() * multY));
	}
	
	//From https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
	//I know, I could have easily done this myself. But I was already on that page and I happened to need this method
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		
		return ext;
	}

	public static String formatHTMLImageTag(String uri, int width, int height){
		return String.format("<html><img src=\"%s\" width=\"%d\" height=\"%d\"></html>", uri, width, height);
	}

	public static void openInBrowser(String uri){
		Desktop desktop = Desktop.getDesktop();
		try{
			desktop.browse(new URI(uri));
		}
		catch(IOException | URISyntaxException e){
			System.err.print("Failed to open URI in browser");
		}
	}

	public static void printException(String message, Exception e){
		System.err.println(message + ": " + e.getClass().getSimpleName() + System.lineSeparator() + e.getMessage());
	}
}
