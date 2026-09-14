package com.practice.FoodDeliverySystem.search;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.FoodDeliverySystem.entities.Address;
import com.practice.FoodDeliverySystem.entities.Restaurant;

public class SearchByProximityStrategy implements SearchStrategy{

	public final Address userAddress;
	public final double maxDistance; // in kilometers
	
	public SearchByProximityStrategy(Address userAddress, double maxDistance) {
		this.userAddress = userAddress;
		this.maxDistance = maxDistance;
	}
	
	@Override
	public List<Restaurant> filter(List<Restaurant> restaurants) {
		return restaurants.stream()
				.filter(restaurant -> restaurant.getAddress().distanceTo(userAddress) <= maxDistance)
				.collect(Collectors.toList());
	}
}
