package com.practice.MusicStreamingSystem.observer;

public interface Subject {
    void addObserver(ArtistObserver observer);
    void removeObserver(ArtistObserver observer);
    void notifyObservers(String message);
}
