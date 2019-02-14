package org.wiizerdofwiierd.discord.battleroyalegenerator.ui.window.user;

import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class JsonFileFilter extends FileFilter{

	@Override
	public boolean accept(File file){
		if(file.isDirectory()) return true;
		
		String extension = Util.getExtension(file);
		if(extension != null && extension.equalsIgnoreCase("json")) return true;
		
		return false;
	}

	@Override
	public String getDescription(){
		return "JSON Files (.json)";
	}
}
