package com.practice.FoodDeliverySystem.entities;

public class MenuItem {

	private final String name;
	private final double price;
	private final String id;
	private boolean isAvailable;
	
	public MenuItem(String name, double price, String id) {
		this.name = name;
		this.price = price;
		this.id = id;
		this.isAvailable = true; // Default availability
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getId() {
		return id;
	}
	
	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}
