package com.practice.MusicStreamingSystem.strategy.playback;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.entities.Song;

public class PremiumPlaybackStrategy implements PlaybackStrategy {

	@Override
	public void play(Song song, Player player) {
		player.setCurrentSong(song);
		System.out.println("Playing song: " + song.getTitle() + " by " + song.getArtist().getName());
	}

}
