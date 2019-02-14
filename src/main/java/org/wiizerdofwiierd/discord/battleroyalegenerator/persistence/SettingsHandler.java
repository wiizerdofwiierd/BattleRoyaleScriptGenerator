package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.CastSize;
import org.wiizerdofwiierd.discord.battleroyalegenerator.script.MemberList;
import sx.blah.discord.handle.obj.IGuild;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SettingsHandler{
	
	public static final File SETTINGS_FILE = new File(System.getProperty("user.dir") + File.separator + "persistence.json");
	
	private Map<String, Settings> guildSettings = new HashMap<>();
	private File lastModified = SETTINGS_FILE;
	
	public SettingsHandler(){}
	
	public Settings getSettingsForGuild(IGuild guild){
		return getSettingsForGuild(guild.getLongID());
	}
	
	public Settings getSettingsForGuild(Long guildId){
		if(this.guildSettings.containsKey(guildId.toString())){
			return this.guildSettings.get(guildId.toString());
		}
		else{
			Settings settings = new Settings();
			this.guildSettings.put(guildId.toString(), settings);
			return settings;
		}
	}

	/**
	 * Loads settings from the default file, or creates it if it does not exist
	 * @return This object, for chaining
	 */
	public SettingsHandler load(){
		return load(SETTINGS_FILE);
	}

	/**
	 * Loads settings from the specified file, or creates it if it does not exist
	 * @param file File to load settings from
	 * @return This object, for chaining
	 */
	public SettingsHandler load(File file){
		System.out.println("Loading persistence file...");
		
		if(!file.exists()){
			System.out.println("No persistence file found! Creating...");
			save(file);
			return this;
		}
		
		Gson gson = new Gson();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String json = reader.lines().collect(Collectors.joining());

			GsonBuilder builder = new GsonBuilder();
			
			JsonDeserializer<Settings> settingsDeserializer = (jsonElement, type, jsonDeserializationContext) -> {
				JsonObject jsonObject = jsonElement.getAsJsonObject();

				boolean showBots = jsonObject.get("showBots").getAsBoolean();
				boolean showCustom = jsonObject.get("showCustom").getAsBoolean();
				boolean showHidden = jsonObject.get("showHidden").getAsBoolean();
				boolean autoGenerateUrl = jsonObject.get("autoGenerateUrl").getAsBoolean();
				boolean autoCopyLink = jsonObject.get("autoCopyLink").getAsBoolean();
				
				CastSize castSize = CastSize.getFromValue(jsonObject.get("castSize").getAsInt());
				if(castSize == null) castSize = CastSize.SIZE_24;
				
				return new Settings(showBots, showCustom, showHidden, autoGenerateUrl, autoCopyLink, castSize, new MemberList());
			};
			builder.registerTypeAdapter(Settings.class, settingsDeserializer);
			
			Type type = new TypeToken<Map<String, Settings>>(){}.getType();
			Map<String, Settings> deserialized = gson.fromJson(json, type);

			this.guildSettings = deserialized;
		}
		catch(IOException e){
			System.err.print("Exception thrown while loading persistence file: " + e.getClass().getSimpleName() + System.lineSeparator()
					+ e.getMessage());
		}

		this.lastModified = file;
		
		System.out.println("File " + SETTINGS_FILE + " loaded.");
		return this;
	}

	/**
	 * Saves settings to the most recently saved file
	 */
	public void save(){
		save(this.lastModified);
	}

	/**
	 * Saves settings to the specified file
	 * @param output File to output
	 */
	public void save(File output){
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
		String json = gson.toJson(guildSettings);

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			writer.write(json);
			System.out.println("Saved persistence file: " + output.getAbsolutePath());
		}
		catch(IOException e){
			System.err.print("Exception thrown while saving persistence file: " + e.getClass().getSimpleName() + System.lineSeparator()
					+ e.getMessage());
		}
		
		this.lastModified = output;
	}
}
