package com.practice.RIdeSharingSystem;

import com.practice.RIdeSharingSystem.enums.DriverStatus;
import com.practice.RIdeSharingSystem.enums.VehicleType;
import com.practice.RIdeSharingSystem.observer.TripObserver;
import com.practice.RIdeSharingSystem.observer.User;

public class Driver extends User implements TripObserver {

	private Location currentLocation;

	private String vehicleDetails;

	private VehicleType vehicleType;

	private DriverStatus status;

	public Driver(String name, String contact, Location currentLocation, String vehicleDetails,
			VehicleType vehicleType) {

		super(name, contact);
		this.currentLocation = currentLocation;
		this.vehicleDetails = vehicleDetails;
		this.vehicleType = vehicleType;
		this.status = DriverStatus.AVAILABLE;
	}

	@Override
	public void update(Ride trip) {
		System.out.println("Driver " + name + " notified about ride: " + trip.getRideId());
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public DriverStatus getStatus() {
		return status;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
	}
}