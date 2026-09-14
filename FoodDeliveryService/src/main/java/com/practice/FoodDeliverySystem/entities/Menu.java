package com.practice.FoodDeliverySystem.entities;

import java.util.HashMap;
import java.util.Map;

public class Menu {

	private final Map<String, MenuItem> items=new HashMap<>();
	
	public void addMenuItem(MenuItem item) {
		items.put(item.getId(), item);
	}
	
	public MenuItem getMenuItem(String id) {
		return items.get(id);
	}
	
	public Map<String, MenuItem> getAllMenuItems() {
		return items;
	}
}
