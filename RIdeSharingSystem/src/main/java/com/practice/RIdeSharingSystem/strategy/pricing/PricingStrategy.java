package com.practice.RIdeSharingSystem.strategy.pricing;

import com.practice.RIdeSharingSystem.Driver;
import com.practice.RIdeSharingSystem.Location;
import com.practice.RIdeSharingSystem.enums.VehicleType;

public interface PricingStrategy {

	 double calculateFare(Location from,Location to, VehicleType vehicleTyper);
	 
}
