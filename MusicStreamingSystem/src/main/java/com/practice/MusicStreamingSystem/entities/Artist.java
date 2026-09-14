package com.practice.MusicStreamingSystem.entities;

import com.practice.MusicStreamingSystem.observer.ArtistObserver;
import com.practice.MusicStreamingSystem.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artist implements Subject {

    private final String name;
    private final List<Album> albums = new ArrayList<>();
    private final List<ArtistObserver> observers = new ArrayList<>();

    public Artist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Artist name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Album createAlbum(String albumName) {
        Album album = new Album(albumName, this);
        albums.add(album);
        notifyObservers("Artist " + name + " created a new album: " + albumName);
        return album;
    }

    public void addTrackToAlbum(Album album, Song song) {
        Objects.requireNonNull(album);
        Objects.requireNonNull(song);

        if (album.getArtist() != this) {
            throw new IllegalArgumentException("Album does not belong to this artist");
        }
        album.addTrack(song);
        notifyObservers("Artist " + name + " added track '" + song.getTitle()
                + "' to album '" + album.getName() + "'");
    }

    public List<Album> getAlbums() {
        return List.copyOf(albums);
    }

    @Override
    public void addObserver(ArtistObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ArtistObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (ArtistObserver observer : List.copyOf(observers)) {
            observer.update(message);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Artist other)) return false;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
