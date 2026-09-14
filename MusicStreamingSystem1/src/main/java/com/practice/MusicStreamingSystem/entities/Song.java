package com.practice.MusicStreamingSystem.entities;

import java.util.List;

public class Song implements Playable {
	private final String id;
	private final String title;
	private final Artist artist;
	private final int duration; // in seconds

	public Song(String id, String title, Artist artist, int duration) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Artist getArtist() {
		return artist;
	}

	public int getDuration() {
		return duration;
	}

	@Override
	public List<Song> getTracks() {
		return List.of(this);
	}

	
}
