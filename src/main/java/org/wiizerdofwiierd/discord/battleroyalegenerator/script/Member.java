package org.wiizerdofwiierd.discord.battleroyalegenerator.script;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

public class Member{
	
	private static final String NAME_REGEX = "[a-zA-Z0-9 ,.'-:!?úóãíáéêç]*$";
	
	private long id;
	private Gender gender;
	private String name;
	private String nickname;
	private String customImgUrl;
	private boolean participating;
	private boolean bot;
	private boolean orphaned;
	private boolean custom;

	/**
	 * Creates a new {@link Member} from an {@link IUser} and generates default values
	 * @param user
	 */
	public Member(IUser user, IGuild guild){
		this(user.getLongID(), Gender.MALE, user.getName(), user.getDisplayName(guild), null, false, user.isBot(), false);
	}

	private Member(long id, Gender gender, String name, String nickname, String imgUrl, boolean participating, boolean bot, boolean custom){
		this.id = id;
		this.gender = gender;
		this.name = name;
		this.nickname = nickname;
		this.customImgUrl = imgUrl;
		this.participating = participating;
		this.bot = bot;
		this.custom = custom;
	}
	
	public static Member createCustomMember(Gender gender, String name, String nickname, String imgUrl){
		return new Member(-1L, gender, name, nickname, imgUrl, false, false, true);
	}
	
	public static boolean validateName(String name){
		return name.matches(NAME_REGEX) && !name.isEmpty();
	}
	
	public long getId(){
		return this.id;
	}
	
	public Gender getGender(){
		return this.gender;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	public String getCustomImgURL(){
		return this.customImgUrl;
	}
	
	public boolean isParticipating(){
		return this.participating;
	}
	
	public boolean isBot(){
		return this.bot;
	}
	
	public boolean isOrphaned(){
		return this.orphaned;
	}
	
	public boolean isCustom(){
		return this.custom;
	}
	
	public void setGender(Gender gender){
		this.gender = gender;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setOrphaned(boolean orphaned){
		this.orphaned = orphaned;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public void setParticipating(boolean participating){
		this.participating = participating;
	}
	
	public void restoreInfo(IUser user, IGuild guild){
		this.gender = Gender.MALE;
		this.name = user.getName();
		this.nickname = user.getDisplayName(guild);
	}
}
