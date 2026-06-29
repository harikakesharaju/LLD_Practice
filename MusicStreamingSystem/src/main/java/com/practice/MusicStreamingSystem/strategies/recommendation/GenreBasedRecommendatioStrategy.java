package com.practice.MusicStreamingSystem.strategies.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.practice.MusicStreamingSystem.entities.Song;

public class GenreBasedRecommendatioStrategy implements RecommendationStrategy {

	@Override
	public List<Song> recommend(List<Song> allSongs) {
		List<Song> shuffled=new ArrayList<>(allSongs);
		Collections.shuffle(shuffled);
		return shuffled.stream().limit(5).collect(Collectors.toList());
	}

}
