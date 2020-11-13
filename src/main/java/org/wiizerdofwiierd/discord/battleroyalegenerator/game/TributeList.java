package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TributeList implements Collection<Tribute>{
	
	private Collection<Tribute> members;
	
	public TributeList(){
		this.members = new ArrayList<>();
	}
	
	public List<Tribute> getMembersByParticipation(boolean participation, Settings settings){
		boolean showBots = settings.showBots.getValue();
		boolean showCustom = settings.showCustom.getValue();
		
		return getMembersByParticipation(participation).stream()
				.filter(m -> (m.isBot() && showBots) || !m.isBot())
				.filter(m -> m.isCustom() && showCustom	 || !m.isCustom())
				.collect(Collectors.toList());
	}
	
	public List<Tribute> getMembersByParticipation(boolean participation){
		return participation ? getParticipatingMembers() : getNonParticipatingMembers();
	}
	
	public List<Tribute> getNonParticipatingMembers(){
		return this.members.stream().filter(m -> !m.isParticipating()).collect(Collectors.toList());
	}
	
	public List<Tribute> getParticipatingMembers(){
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
		guild.getMembers().stream().forEach(m -> userIds.add(m.getIdLong()));
		
		outer:
		for(Member member : guild.getMembers()){
			long id = member.getIdLong();
			
			for(Tribute m : this.members){
				
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

			Tribute tribute = new Tribute(member);
			//If the user has an invalid nickname, but a valid name, set the nickname to the name
			if(!Tribute.validateName(tribute.getNickname()) && Tribute.validateName(tribute.getName())){
				tribute.setNickname(tribute.getName());
			}
			
			members.add(tribute);
		}

		int purgeCount = purgeOrphans();
		System.out.println("Purged " + purgeCount + " orphaned user" + (purgeCount == 1 ? "" : "s"));
		SavedSettingsHandler.getInstance().save();
	}
	
	public int purgeOrphans(){
		List<Tribute> purgeList = this.stream().filter(m -> m.isOrphaned()).collect(Collectors.toList());
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
	public Iterator<Tribute> iterator(){
		return this.members.iterator();
	}

	@Override
	public void forEach(Consumer<? super Tribute> consumer){
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
	public boolean add(Tribute member){
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
	public boolean addAll(Collection<? extends Tribute> collection){
		return this.members.addAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection){
		return this.members.removeAll(collection);
	}

	@Override
	public boolean removeIf(Predicate<? super Tribute> predicate){
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
	public Spliterator<Tribute> spliterator(){
		return this.members.spliterator();
	}

	@Override
	public Stream<Tribute> stream(){
		return this.members.stream();
	}

	@Override
	public Stream<Tribute> parallelStream(){
		return this.members.parallelStream();
	}
}
