package org.wiizerdofwiierd.discord.battleroyalegenerator.game.event;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class EventScenario{

	private static final String SERIALIZATION_DELIMITER = ", ";
	
	public static final int MAX_TRIBUTES = 6;
	
	
	private String text;
	private int numTributes;
	
	private Set<Integer> killed;
	private Set<Integer> killers;
	
	public EventScenario(){
		this("(Scenario text)", 1);
	}
	
	public EventScenario(String text, int numTributes){
		this.text = text;
		this.numTributes = numTributes;

		this.killed = new TreeSet<>();
		this.killers = new TreeSet<>();
	}
	
	public String getText(){
		return this.text;
	}
	
	public int getNumTributes(){
		return this.numTributes;
	}
	
	public Set<Integer> getKilled(){
		return this.killed;
	}
	
	public Set<Integer> getKillers(){
		return this.killers;
	}
	
	public boolean isFatal(){
		return this.killed != null && !this.killed.isEmpty();
	}

	public boolean isKilled(int tributeNum){
		for(int i : this.killed){
			if(i == tributeNum) return true;
		}
		
		return false;
	}

	public boolean isKiller(int tributeNum){
		for(int i : this.killers){
			if(i == tributeNum) return true;
		}
		
		return false;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setNumTributes(int numTributes){
		this.numTributes = numTributes;
	}
	
	public void setKilled(int tributeNum, boolean value){
		if(value){
			this.killed.add(tributeNum);
		}
		else{
			this.killed.remove(tributeNum);
		}
	}
	
	public void setKiller(int tributeNum, boolean value){
		if(value){
			this.killers.add(tributeNum);
		}
		else{
			this.killers.remove(tributeNum);
		}
	}

	public static class ScenarioSerializer implements JsonSerializer<EventScenario>{

		@Override
		public JsonElement serialize(EventScenario scenario, Type type, JsonSerializationContext context){
			var object = new JsonObject();
			
			object.addProperty("text", scenario.text);
			object.addProperty("numTributes", scenario.numTributes);
			object.addProperty("killed", scenario.killed.stream().map(String::valueOf).collect(Collectors.joining(SERIALIZATION_DELIMITER)));
			object.addProperty("killers", scenario.killers.stream().map(String::valueOf).collect(Collectors.joining(SERIALIZATION_DELIMITER)));
			
			return object;
		}
	}

	public static class ScenarioDeserializer implements JsonDeserializer<EventScenario>{
		
		@Override
		public EventScenario deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
			var object = element.getAsJsonObject();
			
			EventScenario scenario = new EventScenario();
			
			scenario.text = object.get("text").getAsString();
			scenario.numTributes = object.get("numTributes").getAsInt();
			scenario.killed = stringToSet(object.get("killed").getAsString());
			scenario.killers = stringToSet(object.get("killers").getAsString());
			
			return scenario;
		}
		
		private Set<Integer> stringToSet(String string){
			Set<Integer> set = new TreeSet<>();
			if(string.isEmpty()) return set;
			
			Arrays.stream(string.split(SERIALIZATION_DELIMITER)).forEach(s -> set.add(Integer.parseInt(s)));
			
			return set;
		}
	}
}
