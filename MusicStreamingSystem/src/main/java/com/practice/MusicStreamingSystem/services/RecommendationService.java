package com.practice.MusicStreamingSystem.services;

import java.util.List;

import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.strategies.recommendation.RecommendationStrategy;

public class RecommendationService {

	private RecommendationStrategy strategy;
	
	public RecommendationService(RecommendationStrategy s) {
		this.strategy=s;
	}
	
	public void setStrategy(RecommendationStrategy s) {
		this.strategy=s;
	}
	
	public List<Song> generateRecommendations(List<Song> all){
		return strategy.recommend(all);
	}
}

