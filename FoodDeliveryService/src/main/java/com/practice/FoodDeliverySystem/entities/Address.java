package com.practice.FoodDeliverySystem.entities;

public class Address {

	private String street;
	private String city;
	private String zipCode;
	private double latitude;;
	private double longitude;
	
	public Address(String street, String city, String zipCode, double latitude, double longitude) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double distanceTo(Address other) {
		
		double lat1 = Math.toRadians(this.latitude);
		double lon1 = Math.toRadians(this.longitude);
		double lat2 = Math.toRadians(other.latitude);
		double lon2 = Math.toRadians(other.longitude);

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;

		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));

		double radiusOfEarthInKm = 6371;
		return c * radiusOfEarthInKm;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getCity() {
		return city;
	}
	
	@Override
	public String toString() {
		return street + ", " + city + ", " + zipCode+", Lat: " + latitude + ", Long: " + longitude;
	}
}
