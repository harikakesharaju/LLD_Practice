package com.practice.MusicStreamingSystem.services;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;
import com.practice.MusicStreamingSystem.strategies.recommendation.RecommendationStrategy;

import java.util.List;
import java.util.Objects;

public class RecommendationService {

    private RecommendationStrategy strategy;

    public RecommendationService(RecommendationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public List<Song> recommend(User user, List<Song> catalog) {
        return strategy.recommend(user, catalog);
    }
}
