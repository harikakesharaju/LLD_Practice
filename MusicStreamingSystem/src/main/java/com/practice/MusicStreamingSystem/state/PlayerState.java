package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;

public interface PlayerState {

	void play(Player player);
	void pause(Player player);
	void stop(Player player);
}
