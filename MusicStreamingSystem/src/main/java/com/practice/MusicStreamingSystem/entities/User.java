package com.practice.MusicStreamingSystem.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.practice.MusicStreamingSystem.observer.ArtistObserver;
import com.practice.MusicStreamingSystem.strategy.playback.PlaybackStrategy;

public class User implements ArtistObserver{

	private final String id;
	private final String name;
	private final PlaybackStrategy playbackStrategy;
	private final Set<Artist> followedArtists=new HashSet<>();
	
	private User(String id,String name, PlaybackStrategy playbackStrategy){
		this.id=id;
		this.name=name;
		this.playbackStrategy=playbackStrategy;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public PlaybackStrategy getPlaybackStrategy() {
		return playbackStrategy;
	}
	
	public void followArtist(Artist artist) {
		followedArtists.add(artist);
		artist.addObserver(this);
	}
	
	@Override
	public void update(Artist artist, Album album) {
		System.out.println("User "+name+" notified about new album "+((Album)album).getTitle()+" by artist "+artist.getName());
	}
	
	public static class UserBuilder{
		private String id;
		private String name;
		private PlaybackStrategy playbackStrategy;
		
		public UserBuilder(String name) {
			this.name = name;
			this.id=UUID.randomUUID().toString();
		}
		public UserBuilder setPlaybackStrategy(PlaybackStrategy playbackStrategy) {
			this.playbackStrategy = playbackStrategy;
			return this;
		}
		
		public User build() {
			return new User(id,name,playbackStrategy);
		}
	}
}
