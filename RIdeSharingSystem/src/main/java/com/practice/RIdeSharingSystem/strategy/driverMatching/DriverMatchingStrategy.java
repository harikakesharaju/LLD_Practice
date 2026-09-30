package com.practice.RIdeSharingSystem.strategy.driverMatching;

import java.util.List;

import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public interface DriverMatchingStrategy {

	Driver findDriver(Location fromLocation, List<Driver> availableDrivers,VehicleType vehicleType);
}
