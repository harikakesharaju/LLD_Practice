package com.practice.MusicStreamingSystem.strategies.playback;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;

public class PremiumPlaybackStrategy implements PlaybackStrategy {

    @Override
    public boolean canPlay(User user, Song song) {
        System.out.println("Premium playback: full access, no ads.");
        return true;
    }
}
