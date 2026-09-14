package com.practice.MusicStreamingSystem.strategies.playback;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;

public class FreePlaybackStrategy implements PlaybackStrategy {

    @Override
    public boolean canPlay(User user, Song song) {
        System.out.println("Free playback: playing with advertisements.");
        System.out.println("Ad: Upgrade to Premium for an ad-free experience.");
        return true;
    }
}
