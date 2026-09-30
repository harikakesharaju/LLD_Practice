package com.practice.RIdeSharingSystem;

import java.util.ArrayList;
import java.util.List;

import com.practice.RIdeSharingSystem.observer.TripObserver;

public class Ride {

	Customer customer;
	Driver driver;
	Location fromLocation;;
	Location toLocation;;
	double fare;
	List<TripObserver> observers = new ArrayList<>();
	
	Ride(Customer customer,Location fromLocation, Location toLocation) {
		this.customer = customer;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}
	
	void addObserver(TripObserver observer) {
		observers.add(observer);
	}
	
	void notifyObservers() {
		for (TripObserver observer : observers) {
			observer.update(this);
		}
	}
	
	
}
