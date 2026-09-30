package com.practice.RIdeSharingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.practice.RIdeSharingSystem.enums.RideStatus;
import com.practice.RIdeSharingSystem.observer.TripObserver;

public class Ride {

    private String rideId;

    private Customer customer;
    private Driver driver;

    private Location fromLocation;
    private Location toLocation;

    private double fare;

    private RideStatus status;

    private List<TripObserver> observers = new ArrayList<>();


    public Ride(
            Customer customer,
            Location fromLocation,
            Location toLocation) {

        this.rideId = UUID.randomUUID().toString();

        this.customer = customer;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;

        this.status = RideStatus.REQUESTED;

        addObserver(customer);
    }


    public void addObserver(TripObserver observer) {
        observers.add(observer);
    }


    private void notifyObservers() {

        for (TripObserver observer : observers) {
            observer.update(this);
        }
    }


    public void setStatus(RideStatus status) {

        this.status = status;

        notifyObservers();
    }


    public String getRideId() {
        return rideId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public RideStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {

        return "Ride{" +
                "rideId='" + rideId + '\'' +
                ", customer=" + customer.name +
                ", driver=" + (driver != null ? driver.name : "Not Assigned") +
                ", fare=" + fare +
                ", status=" + status +
                '}';
    }
}