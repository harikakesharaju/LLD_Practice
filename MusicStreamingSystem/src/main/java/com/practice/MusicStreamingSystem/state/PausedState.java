package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public class PausedState implements PlayerState {

    @Override
    public void play(Player player) {
        System.out.println("Resuming: " + player.getCurrentSong());
        player.playCurrentSongInQueue();
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Already paused.");
    }

    @Override
    public void next(Player player) {
        player.nextTrack();
        player.setState(new PlayingState());
    }

    @Override
    public PlayerStatus getStatus() {
        return PlayerStatus.PAUSED;
    }
}
