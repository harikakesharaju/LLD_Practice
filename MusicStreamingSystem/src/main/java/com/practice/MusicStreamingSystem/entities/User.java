package com.practice.MusicStreamingSystem.entities;

import com.practice.MusicStreamingSystem.enums.SubscriptionTier;
import com.practice.MusicStreamingSystem.observer.ArtistObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements ArtistObserver {

    private final String name;
    private SubscriptionTier subscriptionTier;
    private final Set<Artist> followedArtists = new HashSet<>();
    private final List<Song> playHistory = new ArrayList<>();
    private final List<String> notifications = new ArrayList<>();

    public User(String name, SubscriptionTier subscriptionTier) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        this.name = name;
        this.subscriptionTier = subscriptionTier;
    }

    public String getName() {
        return name;
    }

    public SubscriptionTier getSubscriptionTier() {
        return subscriptionTier;
    }

    public void setSubscriptionTier(SubscriptionTier subscriptionTier) {
        this.subscriptionTier = subscriptionTier;
    }

    public void followArtist(Artist artist) {
        if (artist != null) followedArtists.add(artist);
    }

    public void unfollowArtist(Artist artist) {
        followedArtists.remove(artist);
        if (artist != null) artist.removeObserver(this);
    }

    public void addToHistory(Song song) {
        if (song != null) playHistory.add(song);
    }

    public List<Song> getPlayHistory() {
        return List.copyOf(playHistory);
    }

    public List<String> getNotifications() {
        return List.copyOf(notifications);
    }

    @Override
    public void update(String message) {
        notifications.add(message);
        System.out.println("[NOTIFICATION -> " + name + "] " + message);
    }

    @Override
    public String toString() {
        return name + " (" + subscriptionTier + ")";
    }
}
