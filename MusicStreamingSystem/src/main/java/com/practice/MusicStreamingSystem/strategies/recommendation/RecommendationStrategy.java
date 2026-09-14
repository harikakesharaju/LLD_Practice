package com.practice.MusicStreamingSystem.strategies.recommendation;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;

import java.util.List;

public interface RecommendationStrategy {
    List<Song> recommend(User user, List<Song> catalog);
}
