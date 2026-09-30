package com.practice.RIdeSharingSystem.strategy.driverMatching;

import java.util.List;

import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.DriverStatus;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

	@Override
	public Driver findDriver(Location fromLocation, List<Driver> availableDrivers, VehicleType vehicleType) {

		return availableDrivers.stream()
				.filter(driver -> driver.getVehicleType() == vehicleType)
				.filter(driver -> driver.getStatus() == DriverStatus.AVAILABLE)
				.min((d1, d2) -> Double.compare(d1.getCurrentLocation().distanceTo(fromLocation),
						d2.getCurrentLocation().distanceTo(fromLocation)))
				.orElse(null);
	}
}