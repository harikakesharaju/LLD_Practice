package com.practice.MusicStreamingSystem.strategies.playback;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;

public interface PlaybackStrategy {
    boolean canPlay(User user, Song song);
}
