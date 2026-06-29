package com.practice.CarRentalSystem;

import java.util.List;

public class Store {
    private int storeId;
    private String location;
    private VehicleInventoryMgmt mgmt;

    public Store(int id, String loc, List<Vehicle> lt) {
        this.storeId = id;
        this.location = loc;
        this.mgmt = new VehicleInventoryMgmt();
        for (Vehicle v : lt) {
            mgmt.addVehicle(v);
        }
    }

    public void handleReq(Request req) {
        try {
            mgmt.allotVehicle(req);
        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
        }
    }

    public int calculateFare(Request req) {
        return mgmt.getFare(req);
    }
}
