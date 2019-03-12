package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.reflect.TypeToken;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario.ScenarioDeserializer;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario.ScenarioSerializer;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SavedEventsHandler extends AbstractSerializationHandler<List<GameEvent>>{

	public static final File EVENTS_FILE = new File(System.getProperty("user.dir") + File.separator + "events.json");

	private static SavedEventsHandler instance;

	static{
		instance = new SavedEventsHandler();
	}

	public SavedEventsHandler(){
		super(EVENTS_FILE, new TypeToken<List<GameEvent>>(){}.getType());

		this.object = new ArrayList<>();

		this.registerTypeForSerialization(EventScenario.class, new ScenarioSerializer());
		this.registerTypeForDeserialization(EventScenario.class, new ScenarioDeserializer());
	}

	public static SavedEventsHandler getInstance(){
		return instance;
	}
	
	public List<GameEvent> getEvents(){
		return this.object;
	}
	
	public void addEvent(GameEvent event){
		this.object.add(event);
	}
	
	public void deleteEvent(GameEvent event){
		if(event == null) return;
		this.object.remove(event);
	}
}
