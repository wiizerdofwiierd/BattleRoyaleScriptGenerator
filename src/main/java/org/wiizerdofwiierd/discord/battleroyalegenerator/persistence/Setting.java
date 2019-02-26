package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Setting<T>{
	
	private T value;
	
	private transient List<ISettingChangeListener<T>> listeners;
	
	public Setting(){
		this.listeners = new ArrayList<>();
	}
	
	public Setting(T defaultValue){
		this.value = defaultValue;
		
		this.listeners = new ArrayList<>();
	}
	
	public T getValue(){
		return this.value;
	}

	public void addChangeListener(ISettingChangeListener<T> listener){
		this.listeners.add(listener);
	}
	
	public void setValue(T value){
		T oldValue = this.value;
		this.value = value;

		this.listeners.forEach(l -> l.settingChanged(oldValue, value));
	}

	public static class SettingSerializer implements JsonSerializer<Setting>{

		@Override
		public JsonElement serialize(Setting setting, Type type, JsonSerializationContext context){
			Object value = setting.getValue();
			if(value instanceof Boolean){
				return new JsonPrimitive((Boolean) value);
			}
			else if(value instanceof Character){
				return new JsonPrimitive((Character) value);
			}
			else if(value instanceof Number){
				return new JsonPrimitive(((Number) value));
			}
			else{
				return new JsonPrimitive(value.toString());
			}
		}
	}
	
	public static class SettingDeserializer implements JsonDeserializer<Setting>{

		@Override
		public Setting deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException{
			JsonObject object = new JsonObject();
			object.add("value", element);
			
			return new Gson().fromJson(object, type);
		}
		
		private Class<?> getGenericTypeOf(Type type){
			String typeName = type.getTypeName();
			typeName = typeName.substring(typeName.lastIndexOf('<') + 1, typeName.length() - 1);

			try{
				return Class.forName(typeName);
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			
			return null;
		}
	}
}
