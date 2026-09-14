
package com.practice.FoodDeliverySystem.search;

import java.util.List;

import com.practice.FoodDeliverySystem.entities.Restaurant;

public interface SearchStrategy {

	List<Restaurant> filter(List<Restaurant> restarants);
}
