package com.practice.MusicStreamingSystem.strategies.recommendation;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GenreBasedRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<Song> recommend(User user, List<Song> catalog) {
        if (catalog == null || catalog.isEmpty()) {
            return List.of();
        }

        Map<String, Integer> genreFrequency = new LinkedHashMap<>();

        for (Song song : user.getPlayHistory()) {
            genreFrequency.merge(song.getGenre(), 1, Integer::sum);
        }

        List<Song> result = new ArrayList<>(catalog);

        result.sort(Comparator.comparingInt(
                song -> -genreFrequency.getOrDefault(song.getGenre(), 0)
        ));

        return result;
    }
}
