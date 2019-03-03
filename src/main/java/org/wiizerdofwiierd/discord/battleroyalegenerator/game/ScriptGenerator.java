package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.util.Util;

import java.io.*;
import java.util.stream.Collectors;

public final class ScriptGenerator{
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	private static final String ENTRY_TEMPLATE = "contestants.push([\"%s\", \"%s\", \"%s\", %d]);";
	
	private static final String DEFAULT_OUTPUT = "royale_name_changer.js";

	private JDA client;
	private Guild guild;
	private Settings settings;
	
	public ScriptGenerator(JDA client, Guild guild, Settings settings){
		this.client = client;
		this.guild = guild;
		this.settings = settings;
	}

	public String generateScript(){
		System.out.println("Loading script template...");
		
		InputStreamReader inputStreamReader = Util.getReaderForResource("royale_name_changer.js");
		
		String generated;
		try(BufferedReader reader = new BufferedReader(inputStreamReader)){
			generated = reader.lines().collect(Collectors.joining(LINE_SEPARATOR)).replace("%s", formatMembers());
		}
		catch(IOException e){
			Util.printException("Exception thrown while closing input stream", e);
			return null;
		}

		//File to output the script to
		File output = new File(System.getProperty("user.dir") + File.separator + DEFAULT_OUTPUT);
		
		//Write to the file
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			writer.write(generated);
			System.out.println("Generated file: " + output.getAbsolutePath());
		}
		catch(IOException e){
			Util.printException("Exception thrown while generating output file", e);
		}
		
		return generated;
	}
	
	private String formatMembers(){
		StringBuilder formattedList = new StringBuilder();
		
		getMembers().getMembersByParticipation(true, this.settings).stream()
				.forEach(m -> {
					String name = m.getName();
					String nickname = m.getNickname();
					
					String avatarUrl;
					if(m.isCustom()){
						avatarUrl = m.getCustomImgURL();
					}
					else{
						User user = client.getUserById(m.getId());
//						IUser user = client.getUserByID(m.getId());
						avatarUrl = (user).getAvatarUrl().replace(".webp", ".png");
					}
					
					formattedList
							.append(String.format(ENTRY_TEMPLATE, name, nickname, avatarUrl, m.getGender().ordinal()))
							.append(LINE_SEPARATOR)
							.append("\t");
				});
		
		return formattedList.toString();
	}
	
	private MemberList getMembers(){
		return this.settings.getMembers();
	}
}
