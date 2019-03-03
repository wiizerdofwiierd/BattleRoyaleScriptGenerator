package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberList implements Collection<GameMember>{
	
	private Collection<GameMember> members;
	
	public MemberList(){
		this.members = new ArrayList<>();
	}
	
	public List<GameMember> getMembersByParticipation(boolean participation, Settings settings){
		boolean showBots = settings.showBots.getValue();
		boolean showCustom = settings.showCustom.getValue();
		
		return getMembersByParticipation(participation).stream()
				.filter(m -> (m.isBot() && showBots) || !m.isBot())
				.filter(m -> m.isCustom() && showCustom	 || !m.isCustom())
				.collect(Collectors.toList());
	}
	
	public List<GameMember> getMembersByParticipation(boolean participation){
		return participation ? getParticipatingMembers() : getNonParticipatingMembers();
	}
	
	public List<GameMember> getNonParticipatingMembers(){
		return this.members.stream().filter(m -> !m.isParticipating()).collect(Collectors.toList());
	}
	
	public List<GameMember> getParticipatingMembers(){
		return this.members.stream().filter(m -> m.isParticipating()).collect(Collectors.toList());
	}
	
	public int size(){
		return this.members.size();
	}

	/**
	 * Repopulates the list, adding new members from the specified guild and hiding members not currently in it
	 * @param guild - {@link Guild} used to repopulate the list
	 */
	public void repopulate(Guild guild){
		ArrayList<Long> userIds = new ArrayList<>();
		guild.getMembers().stream().forEach(u -> userIds.add(u.getIdLong()));
		
		outer:
		for(Member u : guild.getMembers()){
			long id = u.getIdLong();
			for(GameMember m : this.members){
				
				//Skip custom users
				if(m.isCustom()) continue;
				
				//TODO: Optimize?
				//Set this user as orphaned if they are not in the specified server
				if(!userIds.contains(m.getId())){
					m.setOrphaned(true);
				}
				
				//Skip this user if they are already in the members list, and set them as not orphaned
				if(m.getId() == id){
					m.setOrphaned(false);
					continue outer;
				}
			}

			GameMember member = new GameMember(u);
			//If the user has an invalid nickname, but a valid name, set the nickname to the name
			if(!GameMember.validateName(member.getNickname()) && GameMember.validateName(member.getName())){
				member.setNickname(member.getName());
			}
			
			members.add(member);
		}

		int purgeCount = purgeOrphans();
		System.out.println("Purged " + purgeCount + " orphaned user" + (purgeCount == 1 ? "" : "s"));
		SavedSettingsHandler.getInstance().save();
	}
	
	public int purgeOrphans(){
		List<GameMember> purgeList = this.stream().filter(m -> m.isOrphaned()).collect(Collectors.toList());
		purgeList.forEach(this::remove);
		
		return purgeList.size();
	}
	
	@Override
	public boolean isEmpty(){
		return this.members.isEmpty();
	}

	@Override
	public boolean contains(Object o){
		return this.members.contains(o);
	}

	@Override
	public Iterator<GameMember> iterator(){
		return this.members.iterator();
	}

	@Override
	public void forEach(Consumer<? super GameMember> consumer){
		this.members.forEach(consumer);
	}

	@Override
	public Object[] toArray(){
		return this.members.toArray();
	}

	@Override
	public <T> T[] toArray(T[] ts){
		return this.members.toArray(ts);
	}

	@Override
	public boolean add(GameMember member){
		return this.members.add(member);
	}

	@Override
	public boolean remove(Object o){
		return this.members.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> collection){
		return this.members.containsAll(collection);
	}

	@Override
	public boolean addAll(Collection<? extends GameMember> collection){
		return this.members.addAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection){
		return this.members.removeAll(collection);
	}

	@Override
	public boolean removeIf(Predicate<? super GameMember> predicate){
		return this.members.removeIf(predicate);
	}

	@Override
	public boolean retainAll(Collection<?> collection){
		return this.members.retainAll(collection);
	}

	@Override
	public void clear(){
		this.members.clear();
	}

	@Override
	public Spliterator<GameMember> spliterator(){
		return this.members.spliterator();
	}

	@Override
	public Stream<GameMember> stream(){
		return this.members.stream();
	}

	@Override
	public Stream<GameMember> parallelStream(){
		return this.members.parallelStream();
	}
}
