package com.practice.RIdeSharingSystem.strategy.pricing;

import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public class VehicleBasedPricingStrategy implements PricingStrategy {

	@Override
	public double calculateFare(Location loc, VehicleType vehicleType,Driver driver) {
		double baseFare = 5.0; // Base fare for all vehicles
		double dis=loc.distanceTo(driver.currentLocation);
		double distanceFare = dis * 2.0; // $2 per unit distance

		switch (vehicleType) {
			case CAR:
				return baseFare + distanceFare + 3.0; // Additional $3 for cars
			case BIKE:
				return baseFare + distanceFare + 1.0; // Additional $1 for bikes
			case AUTO:
				return baseFare + distanceFare + 2.0; // Additional $2 for autos
			default:
				throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
		}
	}

}
