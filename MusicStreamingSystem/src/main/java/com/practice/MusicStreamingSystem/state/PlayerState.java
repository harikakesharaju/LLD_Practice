package com.practice.MusicStreamingSystem.state;

import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;

public interface PlayerState {

    void play(Player player);

    void pause(Player player);

    void next(Player player);

    PlayerStatus getStatus();
}
