package com.practice.FoodDeliverySystem.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.practice.FoodDeliverySystem.entities.Customer;
import com.practice.FoodDeliverySystem.entities.DeliveryAgent;
import com.practice.FoodDeliverySystem.entities.OrderObserver;
import com.practice.FoodDeliverySystem.entities.Restaurant;

public class Order {

	private final String orderId;
	private final Customer customer;
	private final List<OrderItem> items;
	private OrderStatus status;
	private final Restaurant restaurant;
	private DeliveryAgent deliveryAgent;
	private final List<OrderObserver> observers=new ArrayList<>();
	
	public Order( Customer customer, List<OrderItem> items, Restaurant restaurant) {
		this.orderId = UUID.randomUUID().toString();
		this.customer = customer;
		this.items = items;
		this.status = OrderStatus.PENDING;
		this.restaurant = restaurant;
		addObserver(restaurant);
		addObserver(customer);
		
	}
	
	private void addObserver(OrderObserver observer) {
		observers.add(observer);
	}
	
	private void notifyObservers() {
		observers.forEach(observer -> observer.onUpdate(this));
	}
	
	public boolean cancel() {
		if (status == OrderStatus.PENDING ) {
			status = OrderStatus.CANCELLED;
			notifyObservers();
			return true;
		}
		return false;
	}
	
	public void assignDeliveryAgent(DeliveryAgent agent) {
		this.deliveryAgent = agent;
		addObserver(agent);
		agent.setAvailable(false);
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public DeliveryAgent getDeliveryAgent() {
		return deliveryAgent;
	}
	

	public OrderStatus getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
		notifyObservers();
	}
	
}
