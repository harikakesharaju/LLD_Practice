package com.practice.FoodDeliverySystem.entities;

import java.util.UUID;

public class User {
	private final String id;
	private String name;
	private String phone;
	
	public User(String name, String phone) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
