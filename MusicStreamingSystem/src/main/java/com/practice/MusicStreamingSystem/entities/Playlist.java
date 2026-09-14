package com.practice.MusicStreamingSystem.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Playable {

    private final String name;
    private final List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Playlist name cannot be empty");
        }
        this.name = name;
    }

    public void addSong(Song song) {
        if (song != null) songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Song> getTracks() {
        return List.copyOf(songs);
    }
}
