package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class Tribute{
	
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
	 * Creates a new {@link Tribute} from an {@link User} and generates default values
	 * @param member
	 */
	public Tribute(Member member){
		this(member.getIdLong(), Gender.MALE, member.getUser().getName(), member.getEffectiveName(), null, false, member.getUser().isBot(), false);
	}

	private Tribute(long id, Gender gender, String name, String nickname, String imgUrl, boolean participating, boolean bot, boolean custom){
		this.id = id;
		this.gender = gender;
		this.name = name;
		this.nickname = nickname;
		this.customImgUrl = imgUrl;
		this.participating = participating;
		this.bot = bot;
		this.custom = custom;
	}
	
	public static Tribute createCustomMember(Gender gender, String name, String nickname, String imgUrl){
		return new Tribute(-1L, gender, name, nickname, imgUrl, false, false, true);
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
	
	public void restoreInfo(Member member){
		this.gender = Gender.MALE;
		this.name = member.getUser().getName();
		this.nickname = member.getEffectiveName();
	}
}
