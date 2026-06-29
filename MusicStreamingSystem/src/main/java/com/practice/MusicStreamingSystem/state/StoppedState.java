package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public class StoppedState implements PlayerState {

	@Override
	public void play(Player player) {
		System.out.println("Player is now playing.");
		player.changeState(new PlayingState());
		player.setStatus(PlayerStatus.PLAYING);
	}

	@Override
	public void pause(Player player) {
		System.out.println("Player is already stopped. Cannot pause.");
	}

	@Override
	public void stop(Player player) {
		System.out.println("Player is already stopped.");
	}

}
