package com.practice.MusicStreamingSystem.strategies.recommendation;

import java.util.List;

import com.practice.MusicStreamingSystem.entities.Song;

public interface RecommendationStrategy {

	List<Song> recommend(List<Song> allSongs);
}
