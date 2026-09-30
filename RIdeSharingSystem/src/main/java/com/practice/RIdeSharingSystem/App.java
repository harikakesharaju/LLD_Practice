package com.practice.RIdeSharingSystem;

import java.util.ArrayList;
import java.util.List;

import com.practice.RIdeSharingSystem.enums.PaymentMethod;
import com.practice.RIdeSharingSystem.enums.VehicleType;
import com.practice.RIdeSharingSystem.payment.Payment;
import com.practice.RIdeSharingSystem.payment.PaymentStrategy;
import com.practice.RIdeSharingSystem.payment.PaymentStrategyFactory;
import com.practice.RIdeSharingSystem.service.RideService;
import com.practice.RIdeSharingSystem.strategy.driverMatching.DriverMatchingStrategy;
import com.practice.RIdeSharingSystem.strategy.driverMatching.NearestDriverMatchingStrategy;
import com.practice.RIdeSharingSystem.strategy.pricing.PricingStrategy;
import com.practice.RIdeSharingSystem.strategy.pricing.VehicleBasedPricingStrategy;

public class App {

	public static void main(String[] args) {

		// --------------------------
		// 1. Create customers
		// --------------------------
		Customer customer = new Customer("Harika", "9999999999");

		// --------------------------
		// 2. Create drivers
		// --------------------------
		Driver driver1 = new Driver("Driver1", "1111111111", new Location(10, 10), "Swift", VehicleType.CAR);
		Driver driver2 = new Driver("Driver2", "2222222222", new Location(20, 20), "Activa", VehicleType.BIKE);
		List<Driver> drivers = new ArrayList<>();

		drivers.add(driver1);
		drivers.add(driver2);

		// --------------------------
		// 3. Strategies
		// --------------------------
		DriverMatchingStrategy matchingStrategy = new NearestDriverMatchingStrategy();
		PricingStrategy pricingStrategy = new VehicleBasedPricingStrategy();

		// --------------------------
		// 4. Ride service
		// --------------------------
		RideService rideService = new RideService(matchingStrategy, pricingStrategy, drivers);

		// --------------------------
		// 5. Customer requests ride
		// --------------------------
		Ride ride = rideService.requestRide(customer, new Location(11, 11), new Location(30, 30), VehicleType.CAR);

		System.out.println("Fare = " + ride.getFare());

		// --------------------------
		// 6. Start ride
		// --------------------------
		rideService.startRide(ride);

		// --------------------------
		// 7. Complete ride
		// --------------------------
		rideService.completeRide(ride);

		// --------------------------
		// 8. Payment
		// --------------------------
		PaymentStrategy paymentStrategy = PaymentStrategyFactory.getStrategy(PaymentMethod.UPI);
		paymentStrategy.pay(ride.getFare());

	}
}