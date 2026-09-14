package com.practice.MusicStreamingSystem.entities;

import java.util.ArrayList;
import java.util.List;

import com.practice.MusicStreamingSystem.enums.PlayerStatus;
import com.practice.MusicStreamingSystem.state.PlayerState;
import com.practice.MusicStreamingSystem.state.StoppedState;

public class Player {

	private PlayerState state;
	private PlayerStatus status;
	private List<Song> queue=new ArrayList<>();
	private int currentIndex=-1;
	private Song currentSong;
	private User currentUser;
	
	public Player() {
		this.state = new StoppedState();
		this.status=PlayerStatus.STOPPED;
	}
	
	public void load(Playable playable, User user) {
		this.currentUser=user;
		this.queue=playable.getTracks();
		this.currentIndex=0;
		this.state=new StoppedState();
	}
	
	public void playCurrentSongInQueue() {
		if(currentIndex>=0 && currentIndex<queue.size()) {
			currentSong=queue.get(currentIndex);
			currentUser.getPlaybackStrategy().play(currentSong,this);
		}else {
			System.out.println("No song to play.");
		}
	}
	
	public void clickPlay() {
		state.play(this);
	}
	
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}


	public void clickPause() {
		state.pause(this);
	}
	public void clickNext() {
		if(currentIndex<queue.size()-1) {
			currentIndex++;
			playCurrentSongInQueue();
			System.out.println("Playing next song: "+currentSong.getTitle());
		}else {
			System.out.println("No more songs in the queue.");
			state.stop(this);
		}
	}
	public boolean hasQueue() {
		return !queue.isEmpty();
	}
	
	public void changeState(PlayerState newState) {
		this.state = newState;
	}
}
