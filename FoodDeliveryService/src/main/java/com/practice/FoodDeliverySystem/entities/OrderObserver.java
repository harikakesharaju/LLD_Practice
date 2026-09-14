package com.practice.FoodDeliverySystem.entities;

import com.practice.FoodDeliverySystem.orders.Order;

public interface OrderObserver {

	public void onUpdate(Order order);
}
