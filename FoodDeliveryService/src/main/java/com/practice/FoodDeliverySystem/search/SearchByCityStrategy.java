package com.practice.FoodDeliverySystem.search;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.FoodDeliverySystem.entities.Restaurant;

public class SearchByCityStrategy implements SearchStrategy{	
	
	private String city;
	
	public SearchByCityStrategy(String city) {
		this.city = city.toLowerCase();
	}

	@Override
	public List<Restaurant> filter(List<Restaurant> restaurants) {
		return restaurants.stream()
				.filter(restaurant -> restaurant.getAddress().getCity().toLowerCase().equals(city))
				.collect(Collectors.toList());
	}
	
}
		
