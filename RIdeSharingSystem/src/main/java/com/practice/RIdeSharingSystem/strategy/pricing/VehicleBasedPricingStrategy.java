package com.practice.RIdeSharingSystem.strategy.pricing;

import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public class VehicleBasedPricingStrategy implements PricingStrategy {

	@Override
	public double calculateFare(Location fromLocation, Location toLocation, VehicleType vehicleType) {

		double distance = fromLocation.distanceTo(toLocation);
		double baseFare = 5.0;
		double distanceFare = distance * 2.0;
		double vehicleCharge;
		switch (vehicleType) {
		case CAR:
			vehicleCharge = 3.0;
			break;
		case BIKE:
			vehicleCharge = 1.0;
			break;
		case AUTO:
			vehicleCharge = 2.0;
			break;
		default:
			throw new IllegalArgumentException("Unknown vehicle type");
		}
		return baseFare + distanceFare + vehicleCharge;
	}
}