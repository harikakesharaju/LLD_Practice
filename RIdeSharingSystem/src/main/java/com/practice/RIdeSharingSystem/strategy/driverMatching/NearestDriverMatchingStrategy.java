package com.practice.RIdeSharingSystem.strategy.driverMatching;

import java.util.List;

import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.DriverStatus;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

	@Override
	public Driver findDriver(Location fromLocation, List<Driver> availableDrivers, VehicleType vehicleType) {
		Driver nearestDriver = null;
		double minDistance = Double.MAX_VALUE;

		return availableDrivers.stream()
				.filter(driver -> driver.vehicleType == vehicleType)
				.filter(driver -> driver.status == DriverStatus.AVAILABLE)
				.min((driver1, driver2) -> Double.compare(driver1.currentLocation.distanceTo(fromLocation),
						driver2.currentLocation.distanceTo(fromLocation)))
				.orElse(null);

	}

}
