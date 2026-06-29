package com.practice.MusicStreamingSystem.strategy.playback;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.enums.SubscriptionTier;

public interface PlaybackStrategy {

	void play(Song song,Player player);
	
	static PlaybackStrategy getStrategy(SubscriptionTier tier,int songsPlayed) {
		return tier==SubscriptionTier.PREMIUM? new PremiumPlaybackStrategy() : new FreePlaybackStrategy(songsPlayed);
	}
}
