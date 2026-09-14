package com.practice.FoodDeliverySystem.search;

import java.util.List;
import java.util.stream.Collectors;

import com.practice.FoodDeliverySystem.entities.Restaurant;

public class SearchByMenuKeywordStrategy implements SearchStrategy {

	private String keyword;

	public SearchByMenuKeywordStrategy(String keyword) {
		this.keyword = keyword.toLowerCase();
	}

	@Override
	public List<Restaurant> filter(List<Restaurant> restaurants) {
		return restaurants.stream()
				.filter(restaurant -> restaurant.getMenu().getAllMenuItems().values().stream()
						.anyMatch(item -> item.getName().toLowerCase().contains(keyword)))
				.collect(Collectors.toList());
	}

}
