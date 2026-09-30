package com.practice.RIdeSharingSystem.service;

import java.util.List;

import com.practice.RIdeSharingSystem.Customer;
import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.Ride;
import com.practice.RIdeSharingSystem.enums.DriverStatus;
import com.practice.RIdeSharingSystem.enums.RideStatus;
import com.practice.RIdeSharingSystem.enums.VehicleType;
import com.practice.RIdeSharingSystem.strategy.driverMatching.DriverMatchingStrategy;
import com.practice.RIdeSharingSystem.strategy.pricing.PricingStrategy;

public class RideService {

	private DriverMatchingStrategy matchingStrategy;
	private PricingStrategy pricingStrategy;
	private List<Driver> drivers;
	
	public RideService(DriverMatchingStrategy matchingStrategy, PricingStrategy pricingStrategy, List<Driver> drivers) {
		this.matchingStrategy = matchingStrategy;
		this.pricingStrategy = pricingStrategy;
		this.drivers = drivers;
	}

	public Ride requestRide(Customer customer, Location from, Location to, VehicleType vehicleType) {
		// 1. Create ride
		Ride ride = new Ride(customer, from, to);
		// 2. Find driver
		Driver driver = matchingStrategy.findDriver(from, drivers, vehicleType);
		if (driver == null) {
			System.out.println("No driver available");
			ride.setStatus(RideStatus.CANCELLED);
			return ride;
		}
		// 3. Calculate fare
		double fare = pricingStrategy.calculateFare(from, to, vehicleType);
		ride.setFare(fare);
		ride.setDriver(driver);
		driver.setStatus(DriverStatus.UNAVAILABLE);
		ride.setStatus(RideStatus.ACCEPTED);
		return ride;
	}

	public void startRide(Ride ride) {

		if (ride.getStatus() != RideStatus.ACCEPTED) {
			throw new IllegalStateException("Ride cannot be started");
		}
		ride.setStatus(RideStatus.IN_PROGRESS);
	}

	public void completeRide(Ride ride) {
		if (ride.getStatus() != RideStatus.IN_PROGRESS) {
			throw new IllegalStateException("Ride cannot be completed");
		}
		ride.setStatus(RideStatus.COMPLETED);
		ride.getDriver().setStatus(DriverStatus.AVAILABLE);
		ride.getCustomer().addTripToHistory(ride);

		ride.getDriver().addTripToHistory(ride);
	}

	public void cancelRide(Ride ride) {
		if (ride.getStatus() == RideStatus.COMPLETED) {
			throw new IllegalStateException("Completed ride cannot be cancelled");
		}
		ride.setStatus(RideStatus.CANCELLED);
		if (ride.getDriver() != null) {
			ride.getDriver().setStatus(DriverStatus.AVAILABLE);
		}
	}
}