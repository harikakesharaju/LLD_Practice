package com.practice.CarRentalSystem;

public class Vehicle {
	
	String vehicleNo;
	
	 VehicleStatus status;
	 int noseats;
	 VehicleType type;
	 int hourlyCost;

	 public Vehicle(String vehicleNo, VehicleStatus status, int noseats, VehicleType type, int hourlyCost) {
		this.vehicleNo = vehicleNo;
		this.status = status;
		this.noseats = noseats;
		this.type = type;
		this.hourlyCost = hourlyCost;
	}

	 public VehicleStatus getStatus() {
		 return status;
	 }

	 public void setStatus(VehicleStatus status) {
		 this.status = status;
	 }

	 public String getVehicleNo() {
		 return vehicleNo;
	 }

	 public void setVehicleNo(String vehicleNo) {
		 this.vehicleNo = vehicleNo;
	 }

	 public int getNoseats() {
		 return noseats;
	 }

	 public void setNoseats(int noseats) {
		 this.noseats = noseats;
	 }

	 public VehicleType getType() {
		 return type;
	 }

	 public void setType(VehicleType type) {
		 this.type = type;
	 }

	 public int getHourlyCost() {
		 return hourlyCost;
	 }

	 public void setHourlyCost(int hourlyCost) {
		 this.hourlyCost = hourlyCost;
	 }
	
	 
	
	
}
