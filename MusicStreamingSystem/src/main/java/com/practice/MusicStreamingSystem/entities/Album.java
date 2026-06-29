package com.practice.MusicStreamingSystem.entities;

import java.util.ArrayList;
import java.util.List;

public class Album implements Playable {

	private final String title;
	private final List<Song> tracks=new ArrayList<>();
	
	public Album(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void addTrack(Song song) {
		tracks.add(song);
	}
	
	@Override
	public List<Song> getTracks() {
		return tracks;
	}
}
