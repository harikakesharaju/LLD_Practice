package com.practice.RIdeSharingSystem;

public class Location {

	private double latitude;
	private double longitude;
	
	public double distanceTo(Location other) {
		double latDiff = this.latitude - other.latitude;
		double lonDiff = this.longitude - other.longitude;
		return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
	}
}
