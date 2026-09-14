package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public class PlayingState implements PlayerState {

    @Override
    public void play(Player player) {
        System.out.println("Already playing: " + player.getCurrentSong());
    }

    @Override
    public void pause(Player player) {
        player.pausePlayback();
        player.setState(new PausedState());
    }

    @Override
    public void next(Player player) {
        player.nextTrack();
    }

    @Override
    public PlayerStatus getStatus() {
        return PlayerStatus.PLAYING;
    }
}
