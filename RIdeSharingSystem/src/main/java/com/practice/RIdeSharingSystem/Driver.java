package com.practice.RIdeSharingSystem;

import com.practice.RIdeSharingSystem.enums.DriverStatus;
import com.practice.RIdeSharingSystem.enums.VehicleType;
import com.practice.RIdeSharingSystem.observer.TripObserver;
import com.practice.RIdeSharingSystem.observer.User;

public class Driver extends User implements TripObserver {
	
	public Location currentLocation;
	public String vehicleDetails;
	public VehicleType vehicleType;
	
	public DriverStatus status = DriverStatus.AVAILABLE;
	
	
	Driver(String name, String contact, Location currentLocation, String vehicleDetails, VehicleType vehicleType) {
		super(name, contact);
		this.currentLocation = currentLocation;
		this.vehicleDetails = vehicleDetails;
		this.vehicleType = vehicleType;
	}
	

	@Override
	public void update(Ride trip) {
		System.out.println("Rider " + name + " notified about trip: " + trip);
	}
}
