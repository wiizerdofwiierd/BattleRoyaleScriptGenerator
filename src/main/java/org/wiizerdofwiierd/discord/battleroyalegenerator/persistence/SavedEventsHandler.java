package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.reflect.TypeToken;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.AbstractGameEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SavedEventsHandler extends AbstractSerializationHandler<Map<String, AbstractGameEvent>>{

	public static final File EVENTS_FILE = new File(System.getProperty("user.dir") + File.separator + "events.json");

	private static SavedEventsHandler instance;

	static{
		instance = new SavedEventsHandler();
	}

	public SavedEventsHandler(){
		super(EVENTS_FILE, new TypeToken<Map<String, AbstractGameEvent>>(){}.getType());

		this.object = new HashMap<>();
	}

	public static SavedEventsHandler getInstance(){
		return instance;
	}
}
