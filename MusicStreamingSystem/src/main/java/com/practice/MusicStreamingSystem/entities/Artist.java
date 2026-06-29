package com.practice.MusicStreamingSystem.entities;

import java.util.ArrayList;
import java.util.List;

import com.practice.MusicStreamingSystem.observer.Subject;

public class Artist extends Subject {
	private final String id;
	private final String name;
	private final List<Album> albums=new ArrayList<>();

	public Artist(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public void releaseAlbum(Album album) {
		albums.add(album);
		notifyObservers(this, album);
	
	public String getId() {
		return id;
	}

	public String getName() { 
		return name;
	}

}
