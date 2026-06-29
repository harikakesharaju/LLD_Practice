package com.practice.MusicStreamingSystem.observer;

import java.util.ArrayList;
import java.util.List;

import com.practice.MusicStreamingSystem.entities.Album;
import com.practice.MusicStreamingSystem.entities.Artist;
//import java.util.Observer;

public abstract class Subject {
	
	private final List<ArtistObserver> observers = new ArrayList<>();

	public void addObserver(ArtistObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(ArtistObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(Artist artist, Album album) {
		for (ArtistObserver observer : observers) {
			observer.update(artist, album);
		}
	}
}
