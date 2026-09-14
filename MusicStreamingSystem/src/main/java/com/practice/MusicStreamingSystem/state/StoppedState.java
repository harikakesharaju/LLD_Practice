package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public class StoppedState implements PlayerState {

    @Override
    public void play(Player player) {
        player.playCurrentSongInQueue();
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Cannot pause. Player is stopped.");
    }

    @Override
    public void next(Player player) {
        player.nextTrack();
    }

    @Override
    public PlayerStatus getStatus() {
        return PlayerStatus.STOPPED;
    }
}
