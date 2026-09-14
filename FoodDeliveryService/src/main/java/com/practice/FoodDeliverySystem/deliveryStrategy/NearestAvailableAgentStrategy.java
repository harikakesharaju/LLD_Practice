package com.practice.FoodDeliverySystem.deliveryStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.practice.FoodDeliverySystem.entities.Address;
import com.practice.FoodDeliverySystem.entities.DeliveryAgent;
import com.practice.FoodDeliverySystem.orders.Order;

public class NearestAvailableAgentStrategy implements DeliveryAssignmentStrategy {

	@Override
	public Optional<DeliveryAgent> assignDeliveryAgent(Order order, List<DeliveryAgent> availableAgents) {
		Address customerAddress = order.getCustomer().getAddress();
		Address restaurantAddress = order.getRestaurant().getAddress();
		
		return  availableAgents.stream()
				.filter(DeliveryAgent::isAvailable)
				.min(Comparator.comparingDouble(agent->calculateDistance(agent,restaurantAddress,customerAddress)));
	}

	private double calculateDistance(DeliveryAgent agent, Address restaurantAddress, Address customerAddress) {
		// Calculate the distance from the agent to the restaurant and then to the customer
		double distanceToRestaurant = agent.getCurrentLocation().distanceTo(restaurantAddress);
		double distanceToCustomer = restaurantAddress.distanceTo(customerAddress);
		return distanceToRestaurant + distanceToCustomer;
	}
}
