package com.practice.FoodDeliverySystem.entities;

import java.util.UUID;

import com.practice.FoodDeliverySystem.orders.Order;

public class Restaurant implements OrderObserver {
	
	private final String id;
	private String name;
	private Address address;
	private Menu menu;
	
	public Restaurant(String id,String name, Address address) {
		this.id=UUID.randomUUID().toString();
		this.name = name;
		this.address = address;
		this.menu = new Menu();
	}
	
	public void addMenuItem(MenuItem item) {
		menu.addMenuItem(item);
	}
	
	public Menu getMenu() {
		return menu;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	@Override
	public void onUpdate(Order order) {
		System.out.println("Restaurant " + name + " received update for Order ID: " + order.getOrderId() + ", Status: " + order.getStatus().toString());
	}

}
