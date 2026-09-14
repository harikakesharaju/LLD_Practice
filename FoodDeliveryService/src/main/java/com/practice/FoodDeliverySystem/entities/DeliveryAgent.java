package com.practice.FoodDeliverySystem.entities;

import java.util.concurrent.atomic.AtomicBoolean;

import com.practice.FoodDeliverySystem.orders.Order;
import com.practice.FoodDeliverySystem.orders.OrderStatus;

public class DeliveryAgent extends User implements OrderObserver {

	private final AtomicBoolean isAvailable=new AtomicBoolean(true);
	private Address currentLocation;

	public DeliveryAgent(String name, String contact,Address address) {
		super(name,contact);
		this.currentLocation = address;
	}

	public synchronized boolean isAvailable() {
		return this.isAvailable.get();
	}

	public void setAvailable(boolean available) {
		this.isAvailable.set(available);
	}

	public void setCurrentLocation(Address currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	public Address getCurrentLocation() {
		return currentLocation;
	}
	
	@Override
	public void onUpdate(Order order) {
		if (order.getStatus() == OrderStatus.READY_FOR_PICKUP && isAvailable.get()) {
			// Logic to accept the order for delivery
			this.isAvailable.set(false);  // Mark as unavailable after accepting an order
			System.out.println("Delivery Agent " + getName() + " has accepted the order: " + order.getOrderId());
		} else if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
			this.isAvailable.set(true);  // Mark as available after delivery or cancellation
			System.out.println("Delivery Agent " + getName() + " is now available.");
		}
	}
	
}