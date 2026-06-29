package com.practice.MusicStreamingSystem.command;

import com.practice.MusicStreamingSystem.entities.Player;

public class NextTrackCommand implements Command{

	private final Player player;
	
	public NextTrackCommand(Player player) {
		this.player=player;
	}
	
	@Override
	public void execute() {
		player.clickNext();
	}
}
