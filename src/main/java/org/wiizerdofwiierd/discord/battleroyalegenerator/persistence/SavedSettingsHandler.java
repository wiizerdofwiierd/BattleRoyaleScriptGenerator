package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.entities.Guild;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Setting.SettingDeserializer;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Setting.SettingSerializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SavedSettingsHandler extends AbstractSerializationHandler<Map<String, Settings>>{

	public static final File SETTINGS_FILE = new File(System.getProperty("user.dir") + File.separator + "persistence.json");

	private static SavedSettingsHandler instance;

	static{
		instance = new SavedSettingsHandler();
	}
	
	public SavedSettingsHandler(){
		super(SETTINGS_FILE, new TypeToken<Map<String, Settings>>(){}.getType());
		
		this.object = new HashMap<>();
		
		this.registerTypeForSerialization(Setting.class, new SettingSerializer());
		this.registerTypeForDeserialization(Setting.class, new SettingDeserializer());
	}

	public static SavedSettingsHandler getInstance(){
		return instance;
	}

	public Settings getSettingsForGuild(Guild guild){
		return getSettingsForGuild(guild.getIdLong());
	}

	public Settings getSettingsForGuild(Long guildId){
		if(this.object.containsKey(guildId.toString())){
			return this.object.get(guildId.toString());
		}
		else{
			Settings settings = new Settings();
			this.object.put(guildId.toString(), settings);
			return settings;
		}
	}
}
