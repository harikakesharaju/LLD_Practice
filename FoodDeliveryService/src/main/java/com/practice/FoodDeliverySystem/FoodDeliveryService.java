package com.practice.FoodDeliverySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.practice.FoodDeliverySystem.deliveryStrategy.DeliveryAssignmentStrategy;
import com.practice.FoodDeliverySystem.entities.Address;
import com.practice.FoodDeliverySystem.entities.Customer;
import com.practice.FoodDeliverySystem.entities.DeliveryAgent;
import com.practice.FoodDeliverySystem.entities.Restaurant;
import com.practice.FoodDeliverySystem.orders.Order;
import com.practice.FoodDeliverySystem.orders.OrderItem;
import com.practice.FoodDeliverySystem.orders.OrderStatus;
import com.practice.FoodDeliverySystem.search.SearchByCityStrategy;
import com.practice.FoodDeliverySystem.search.SearchByMenuKeywordStrategy;
import com.practice.FoodDeliverySystem.search.SearchByProximityStrategy;

public class FoodDeliveryService {

	private static volatile FoodDeliveryService instance;
	private final Map<String,Customer> customers=new ConcurrentHashMap<>();
	private final Map<String,Restaurant> restaurants=new ConcurrentHashMap<>();
	private final Map<String ,DeliveryAgent> deliveryAgents=new ConcurrentHashMap<>();
	private final Map<String,Order> orders=new ConcurrentHashMap<>();
	private DeliveryAssignmentStrategy deliveryAssignmentStrategy;
	private SearchByCityStrategy searchByCityStrategy;
	private SearchByMenuKeywordStrategy searchByMenuStrategy;
	private SearchByProximityStrategy searchByProximityStrategy;
	
	
	private FoodDeliveryService() {
	}
	
	public static FoodDeliveryService getInstance() {
		if(instance==null) {
			synchronized(FoodDeliveryService.class) {
				if(instance==null) {
					instance=new FoodDeliveryService();
				}
			}
		}
		return instance;
	}
	
	public void setDeliveryAssignmentStrategy(DeliveryAssignmentStrategy strategy) {
		this.deliveryAssignmentStrategy=strategy;
	}
	
	public Customer registerCustomer(String name,String phone, Address address) {
		Customer customer=new Customer(name,phone,address);
		customers.put(customer.getId(), customer);
		return customer;
	}
	
	public Restaurant registerRestaurant(String id,String name, Address address) {
		Restaurant restaurant=new Restaurant(id,name, address);
		restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}
	
	public DeliveryAgent registerDeliveryAgent(String name,String phone, Address address) {
		DeliveryAgent agent=new DeliveryAgent(name,phone,address);
		deliveryAgents.put(agent.getId(), agent);
		return agent;
	}
	
	public Order placeOrder(String customerId,String restaurantId,List<OrderItem> items) {
		Customer customer=customers.get(customerId);
		Restaurant restaurant=restaurants.get(restaurantId);
		if(customer==null || restaurant==null) {
			throw new IllegalArgumentException("Invalid customer or restaurant ID");
		}
		Order order=new Order(customer,items,restaurant);
		orders.put(order.getOrderId(), order);
		order.setStatus(OrderStatus.PENDING);
		
		customer.addOrderToHistory(order);
		return order;
	}
	
	public void updateOrderStatus(String orderId, OrderStatus status) {
		Order order=orders.get(orderId);
		if(order==null) {
			throw new IllegalArgumentException("Invalid order ID");
		}
		order.setStatus(status);
		
		if(status==OrderStatus.READY_FOR_PICKUP) {
			assignDelivery(order);
		}
	}
	
	public void cancelOrder(String orderId) {
		Order order=orders.get(orderId);
		if(order==null) {
			throw new IllegalArgumentException("Invalid order ID");
		}
		boolean cancelled=order.cancel();
		if(cancelled) {
			System.out.println("Order " + orderId + " has been cancelled.");
		}else {
			System.out.println("Order " + orderId + " cannot be cancelled.");
		}
	}
	
	private void assignDelivery(Order order) {
		List<DeliveryAgent> availableAgents=new ArrayList<>(deliveryAgents.values());
				
		deliveryAssignmentStrategy.assignDeliveryAgent(order, availableAgents).ifPresent(
				agent->{
					order.assignDeliveryAgent(agent);
					System.out.println("Delivery Agent " + agent.getName() + " assigned to Order ID: " + order.getOrderId());
					order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
				}
		);
	}
	
	public List<Restaurant> searchRestaurantsByCity(String city) {
		searchByCityStrategy=new SearchByCityStrategy(city);
		
		return searchByCityStrategy.filter(restaurants.values());
	}
	
	
}
