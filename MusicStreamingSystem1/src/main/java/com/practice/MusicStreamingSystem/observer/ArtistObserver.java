package com.practice.MusicStreamingSystem.observer;

import com.practice.MusicStreamingSystem.entities.Album;
import com.practice.MusicStreamingSystem.entities.Artist;

public interface ArtistObserver {
	
	void update(Artist artist, Album album);

}
