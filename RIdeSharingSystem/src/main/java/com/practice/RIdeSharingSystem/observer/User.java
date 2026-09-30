package com.practice.RIdeSharingSystem.observer;

import java.util.ArrayList;
import java.util.List;

import com.practice.RIdeSharingSystem.Ride;

public abstract class User implements TripObserver {

	 public String name;
	public String contact;
	List<Ride> tripHistory = new ArrayList<>();

	public User(String name, String contact) {
		this.name = name;
		this.contact = contact;
	}

	void addTripToHistory(Ride trip) {
		tripHistory.add(trip);
	}
	


}
