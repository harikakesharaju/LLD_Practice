package com.practice.MusicStreamingSystem;

import com.practice.MusicStreamingSystem.entities.Album;
import com.practice.MusicStreamingSystem.entities.Artist;
import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;
import com.practice.MusicStreamingSystem.enums.SubscriptionTier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicStreamingSystem {

    private final List<User> users = new ArrayList<>();
    private final List<Artist> artists = new ArrayList<>();

    public User createUser(String name, SubscriptionTier tier) {
        User user = new User(name, tier);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(Artist artist, String albumName) {
        Objects.requireNonNull(artist);
        return artist.createAlbum(albumName);
    }

    public Song createSong(String title, Artist artist, String genre, int durationSeconds) {
        return new Song(title, artist, genre, durationSeconds);
    }

    public void addTrackToAlbum(Artist artist, Album album, Song song) {
        artist.addTrackToAlbum(album, song);
    }

    public void followArtist(User user, Artist artist) {
        artist.addObserver(user);
        user.followArtist(artist);
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public List<Artist> getArtists() {
        return List.copyOf(artists);
    }
}
