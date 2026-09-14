package com.practice.FoodDeliverySystem.entities;

import java.util.ArrayList;
import java.util.List;

import com.practice.FoodDeliverySystem.orders.Order;

public class Customer extends User implements OrderObserver {
	
	private Address address;
	private List<Order> orderHistory=new ArrayList<>();

	public Customer(String name, String phone,Address address) {
		super(name, phone);
		this.address = address;
	}

	public void addOrderToHistory(Order order) {
		this.orderHistory.add(order);
	}
	
	
	public Address getAddress() {
		return address;
	}

	public List<Order> getOrderHistory() {
		return orderHistory;
	}

	@Override
	public void onUpdate(Order order) {
		// Notify the customer about the order status update
		System.out.println("Notification to " + this.getName() + ": Your order " + order.getOrderId() + " is now " + order.getStatus().toString());
	}
	



}
