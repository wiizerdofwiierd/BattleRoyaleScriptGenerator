package org.wiizerdofwiierd.discord.battleroyalegenerator.script;

public enum Gender{

	FEMALE("Female"),
	MALE("Male");
	
	private String friendlyName;
	
	Gender(String friendlyName){
		this.friendlyName = friendlyName;
	}
	
	public static Gender get(String string){
		for(Gender g : values()){
			if(g.friendlyName.equals(string)){
				return g;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString(){
		return this.friendlyName;
	}
}
