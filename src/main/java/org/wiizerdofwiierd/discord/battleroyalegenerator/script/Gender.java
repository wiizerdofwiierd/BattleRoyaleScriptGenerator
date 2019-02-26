package org.wiizerdofwiierd.discord.battleroyalegenerator.script;

public enum Gender{

	FEMALE("Female", "her", "her"),
	MALE("Male", "him", "his");
	
	private String name;
	private String possessive;
	private String pronoun;
	
	Gender(String name, String pronoun, String possessive){
		this.name = name;
		this.pronoun = pronoun;
		this.possessive = possessive;
	}
	
	public static Gender get(String string){
		for(Gender g : values()){
			if(g.name.equals(string)){
				return g;
			}
		}
		
		return null;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPronoun(){
		return this.pronoun;
	}
	
	public String getPossessivePronoun(){
		return this.possessive;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
