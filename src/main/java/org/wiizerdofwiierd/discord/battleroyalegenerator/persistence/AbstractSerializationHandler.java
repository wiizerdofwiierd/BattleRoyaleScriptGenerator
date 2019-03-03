package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class AbstractSerializationHandler<T>{
	
	protected transient File lastModified;
	protected transient Type objectType;
	
	protected transient GsonBuilder gsonSerializer;
	protected transient GsonBuilder gsonDeserializer;
	
	protected T object;
	
	public AbstractSerializationHandler(File defaultFile, Type objectType){
		this.lastModified = defaultFile;
		this.objectType = objectType;
		
		this.gsonSerializer = new GsonBuilder();
		this.gsonDeserializer = new GsonBuilder();
	}

	/**
	 * Loads settings from the default file, or creates it if it does not exist
	 * @return This object, for chaining
	 */
	public AbstractSerializationHandler load(){
		return load(this.lastModified);
	}

	/**
	 * Loads settings from the specified file, or creates it if it does not exist
	 * @param file File to load settings from
	 * @return This object, for chaining
	 */
	public AbstractSerializationHandler load(File file){
		String displayPath = getDisplayPath(file);
		
		System.out.println("Loading file " + displayPath + "...");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		if(!file.exists()){
			System.out.println("File " + displayPath + " not found! Creating...");
			save(file);
			return this;
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String json = reader.lines().collect(Collectors.joining());

			Gson gson = this.gsonDeserializer.create();
			T deserialized = gson.fromJson(json, this.objectType);

			this.object = deserialized;
		}
		catch(IOException e){
			System.err.print("Exception thrown while loading file: " + 
					e.getClass().getSimpleName() + System.lineSeparator() + e.getMessage());
		}

		this.lastModified = file;
		
		stopWatch.stop();
		long millis = stopWatch.getTime(TimeUnit.MILLISECONDS);

		System.out.printf("File %s loaded in %d millisecond(s)%n", displayPath, millis);
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
		Gson gson = this.gsonSerializer.setPrettyPrinting().create();
		String json = gson.toJson(this.object);

		try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
			writer.write(json);
			System.out.println("Saved file: " + getDisplayPath(output));
		}
		catch(IOException e){
			System.err.printf("Exception thrown while saving file: %s%n%s%n", e.getClass().getSimpleName(), e.getMessage());
		}

		this.lastModified = output;
	}
	
	public File getLastModifiedFile(){
		return this.lastModified;
	}
	
	public void registerTypeForSerialization(Class<?> baseType, @Nullable Object typeAdapter){
		this.gsonSerializer.registerTypeHierarchyAdapter(baseType, typeAdapter);
	}
	
	public void registerTypeForDeserialization(Class<?> baseType, @Nullable Object typeAdapter){
		this.gsonDeserializer.registerTypeHierarchyAdapter(baseType, typeAdapter);
	}
	
	protected String getDisplayPath(File file){
		return file.getParent().equals(System.getProperty("user.dir")) ? file.getName() : file.getAbsolutePath();
	}
}
