package org.wiizerdofwiierd.discord.battleroyalegenerator.game;

import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.Settings;
import org.wiizerdofwiierd.discord.battleroyalegenerator.persistence.SavedSettingsHandler;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberList implements Collection<Member>{
	
	private Collection<Member> members;
	
	public MemberList(){
		this.members = new ArrayList<>();
	}
	
	public List<Member> getMembersByParticipation(boolean participation, Settings settings){
		boolean showBots = settings.showBots.getValue();
		boolean showCustom = settings.showCustom.getValue();
		
		return getMembersByParticipation(participation).stream()
				.filter(m -> (m.isBot() && showBots) || !m.isBot())
				.filter(m -> m.isCustom() && showCustom	 || !m.isCustom())
				.collect(Collectors.toList());
	}
	
	public List<Member> getMembersByParticipation(boolean participation){
		return participation ? getParticipatingMembers() : getNonParticipatingMembers();
	}
	
	public List<Member> getNonParticipatingMembers(){
		return this.members.stream().filter(m -> !m.isParticipating()).collect(Collectors.toList());
	}
	
	public List<Member> getParticipatingMembers(){
		return this.members.stream().filter(m -> m.isParticipating()).collect(Collectors.toList());
	}
	
	public int size(){
		return this.members.size();
	}

	/**
	 * Repopulates the list, adding new members from the specified guild and hiding members not currently in it
	 * @param guild - {@link IGuild} used to repopulate the list
	 */
	public void repopulate(IGuild guild){
		ArrayList<Long> userIds = new ArrayList<>();
		guild.getUsers().stream().forEach(u -> userIds.add(u.getLongID()));
		
		outer:
		for(IUser user : guild.getUsers()){
			long id = user.getLongID();
			for(Member m : this.members){
				
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

			Member member = new Member(user, guild);
			//If the user has an invalid nickname, but a valid name, set the nickname to the name
			if(!Member.validateName(member.getNickname()) && Member.validateName(member.getName())){
				member.setNickname(member.getName());
			}
			
			members.add(member);
		}

		int purgeCount = purgeOrphans();
		System.out.println("Purged " + purgeCount + " orphaned user" + (purgeCount == 1 ? "" : "s"));
		SavedSettingsHandler.getInstance().save();
	}
	
	public int purgeOrphans(){
		List<Member> purgeList = this.stream().filter(m -> m.isOrphaned()).collect(Collectors.toList());
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
	public Iterator<Member> iterator(){
		return this.members.iterator();
	}

	@Override
	public void forEach(Consumer<? super Member> consumer){
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
	public boolean add(Member member){
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
	public boolean addAll(Collection<? extends Member> collection){
		return this.members.addAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection){
		return this.members.removeAll(collection);
	}

	@Override
	public boolean removeIf(Predicate<? super Member> predicate){
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
	public Spliterator<Member> spliterator(){
		return this.members.spliterator();
	}

	@Override
	public Stream<Member> stream(){
		return this.members.stream();
	}

	@Override
	public Stream<Member> parallelStream(){
		return this.members.parallelStream();
	}
}
