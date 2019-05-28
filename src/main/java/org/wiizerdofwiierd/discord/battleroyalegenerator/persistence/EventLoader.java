package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventContext;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.EventScenario;
import org.wiizerdofwiierd.discord.battleroyalegenerator.game.event.GameEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class EventLoader{
	
	public void load(File file, EventContext context){
		
		try(FileReader reader = new FileReader(file)){
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(GameEvent.class, new EventDeserializer(context)).create();
			
			SavedEventsHandler instance = SavedEventsHandler.getInstance();
			
			List<GameEvent> events = gson.fromJson(reader, new TypeToken<List<GameEvent>>(){}.getType());
			for(GameEvent e : events){
				instance.addEvent(e);
			}
			
			instance.save();
		}
		catch(IOException e){
			
		}
	}

	public static class EventDeserializer implements JsonDeserializer<GameEvent>{

		private EventContext context;

		public EventDeserializer(EventContext context){
			this.context = context;
		}

		@Override
		public GameEvent deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
			var object = element.getAsJsonObject();

			EventScenario scenario = new EventScenario();
			GameEvent event = new GameEvent(null, this.context, scenario);

			scenario.setText(object.get("text").getAsString());

			if(object.has("killer")){
				String value = object.get("killer").getAsString();
				
				if(!value.equals("None"))
					scenario.setKillers(stringToSet(object.get("killer").getAsString()));
			}

			if(object.has("killed")){
				String value = object.get("killed").getAsString();

				if(!value.equals("None"))
					scenario.setKilled(stringToSet(object.get("killed").getAsString()));
			}
			
			return event;
		}

		private Set<Integer> stringToSet(String string){
			Set<Integer> set = new TreeSet<>();
			if(string.isEmpty()) return set;

			Arrays.stream(string.split(", ")).forEach(s -> set.add(Integer.parseInt(s.replaceAll("Player", ""))));

			return set;
		}
	}
}
