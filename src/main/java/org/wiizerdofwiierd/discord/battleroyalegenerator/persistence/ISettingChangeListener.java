package org.wiizerdofwiierd.discord.battleroyalegenerator.persistence;

public interface ISettingChangeListener<T>{
	
	void settingChanged(T oldValue, T newValue);
}
