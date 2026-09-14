package com.practice.MusicStreamingSystem.strategy.playback;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public class FreePlaybackStrategy implements PlaybackStrategy {

	private int songsPlayed;
	private static final int SONGS_BEFORE_AD = 3;

	public FreePlaybackStrategy(int songsPlayed) {
		this.songsPlayed = songsPlayed;
	}

	@Override
	public void play(Song song, Player player) {
		if (songsPlayed >0 && songsPlayed%SONGS_BEFORE_AD == 0) {
			System.out.println("Playing ad... Please wait or buy premium to skip ads.");
		}else {
			player.setCurrentSong(song);
			player.setStatus(PlayerStatus.PLAYING);
			songsPlayed++;
		}
	}

}
