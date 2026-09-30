package com.practice.RIdeSharingSystem;

import com.practice.RIdeSharingSystem.observer.TripObserver;
import com.practice.RIdeSharingSystem.observer.User;

public class Customer extends User implements TripObserver {

    public Customer(String name, String contact) {
        super(name, contact);
    }

    @Override
    public void update(Ride trip) {

        System.out.println(
                "Customer " + name +
                " notified: Ride " +
                trip.getRideId() +
                " status changed to " +
                trip.getStatus()
        );
    }
}