package com.practice.FoodDeliverySystem.deliveryStrategy;

import java.util.List;
import java.util.Optional;

import com.practice.FoodDeliverySystem.entities.DeliveryAgent;
import com.practice.FoodDeliverySystem.orders.Order;

public interface DeliveryAssignmentStrategy {

	public Optional<DeliveryAgent> assignDeliveryAgent(Order order, List<DeliveryAgent> availableAgents);
}
