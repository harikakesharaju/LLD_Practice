package com.practice.MusicStreamingSystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album implements Playable {

    private final String name;
    private final Artist artist;
    private final List<Song> tracks = new ArrayList<>();

    public Album(String name, Artist artist) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Album name cannot be empty");
        }
        this.name = name;
        this.artist = Objects.requireNonNull(artist);
    }

    public void addTrack(Song song) {
        if (song == null) throw new IllegalArgumentException("Song cannot be null");
        if (!song.getArtist().equals(artist)) {
            throw new IllegalArgumentException("Artist can only add their own songs to this album");
        }
        if (!tracks.contains(song)) {
            tracks.add(song);
        }
    }

    public Artist getArtist() {
        return artist;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Song> getTracks() {
        return List.copyOf(tracks);
    }

    @Override
    public String toString() {
        return name + " by " + artist.getName();
    }
}
