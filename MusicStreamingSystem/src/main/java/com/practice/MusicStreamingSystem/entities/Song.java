package com.practice.MusicStreamingSystem.entities;

import java.util.Objects;

public class Song {

    private final String title;
    private final Artist artist;
    private final String genre;
    private final int durationSeconds;

    public Song(String title, Artist artist, String genre, int durationSeconds) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Song title cannot be empty");
        }
        if (durationSeconds <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.title = title;
        this.artist = Objects.requireNonNull(artist);
        this.genre = Objects.requireNonNull(genre);
        this.durationSeconds = durationSeconds;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    @Override
    public String toString() {
        return title + " - " + artist.getName();
    }
}
