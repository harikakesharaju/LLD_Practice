package com.practice.RIdeSharingSystem;

import com.practice.RIdeSharingSystem.observer.TripObserver;
import com.practice.RIdeSharingSystem.observer.User;

public class Customer extends User implements TripObserver {


	
	Customer(String name, String contact) {
		super(name, contact);
	}
	

	@Override
	public void update(Ride trip) {
		System.out.println("Rider " + name + " notified about trip: " + trip);
	}

}
