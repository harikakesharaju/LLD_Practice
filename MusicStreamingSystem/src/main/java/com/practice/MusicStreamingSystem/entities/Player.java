package com.practice.MusicStreamingSystem.entities;

import com.practice.MusicStreamingSystem.enums.PlayerStatus;
import com.practice.MusicStreamingSystem.enums.SubscriptionTier;
import com.practice.MusicStreamingSystem.state.PausedState;
import com.practice.MusicStreamingSystem.state.PlayerState;
import com.practice.MusicStreamingSystem.state.PlayingState;
import com.practice.MusicStreamingSystem.state.StoppedState;
import com.practice.MusicStreamingSystem.strategies.playback.FreePlaybackStrategy;
import com.practice.MusicStreamingSystem.strategies.playback.PlaybackStrategy;
import com.practice.MusicStreamingSystem.strategies.playback.PremiumPlaybackStrategy;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private PlayerState state;
    private PlayerStatus status;
    private List<Song> queue = new ArrayList<>();
    private int currentIndex = -1;
    private Song currentSong;
    private User currentUser;
    private PlaybackStrategy playbackStrategy;

    public Player() {
        this.state = new StoppedState();
        this.status = PlayerStatus.STOPPED;
    }

    public void load(Playable playable, User user) {
        if (playable == null || user == null) {
            throw new IllegalArgumentException("Playable and user are required");
        }

        this.currentUser = user;
        this.queue = new ArrayList<>(playable.getTracks());
        this.currentIndex = queue.isEmpty() ? -1 : 0;
        this.currentSong = currentIndex == -1 ? null : queue.get(currentIndex);

        this.playbackStrategy = user.getSubscriptionTier() == SubscriptionTier.PREMIUM
                ? new PremiumPlaybackStrategy()
                : new FreePlaybackStrategy();

        setState(new StoppedState());
        System.out.println("Loaded: " + playable.getName());
    }

    public void clickPlay() {
        state.play(this);
    }

    public void clickPause() {
        state.pause(this);
    }

    public void clickNext() {
        state.next(this);
    }

    public void playCurrentSongInQueue() {
        if (currentSong == null) {
            System.out.println("No song available.");
            return;
        }

        if (playbackStrategy.canPlay(currentUser, currentSong)) {
            System.out.println("Playing: " + currentSong);
            currentUser.addToHistory(currentSong);
        }
    }

    public void nextTrack() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        if (currentIndex + 1 >= queue.size()) {
            System.out.println("Reached end of queue.");
            return;
        }

        currentIndex++;
        currentSong = queue.get(currentIndex);
        System.out.println("Next track: " + currentSong);

        if (status == PlayerStatus.PLAYING) {
            playCurrentSongInQueue();
        }
    }

    public void pausePlayback() {
        if (currentSong != null) {
            System.out.println("Paused: " + currentSong);
        }
    }

    public void stopPlayback() {
        System.out.println("Player stopped.");
    }

    public void setState(PlayerState state) {
        this.state = state;
        this.status = state.getStatus();
    }

    public PlayerState getState() {
        return state;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public PlaybackStrategy getPlaybackStrategy() {
        return playbackStrategy;
    }

    public List<Song> getQueue() {
        return List.copyOf(queue);
    }
}
