package com.practice.CarRentalSystem;

import java.time.LocalDateTime;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Vehicle v1 = new Vehicle("V1", VehicleStatus.AVAILABLE, 4, VehicleType.REGULAR, 500);
        Vehicle v2 = new Vehicle("V2", VehicleStatus.AVAILABLE, 2, VehicleType.PREMIUM, 1000);

        Store store = new Store(1, "Hyderabad", Arrays.asList(v1, v2));
        User user = new User(101, "Alice");

        Request req = new Request(1, user, 4, VehicleType.REGULAR, "Hyderabad",
                LocalDateTime.now(), LocalDateTime.now().plusHours(3));

        store.handleReq(req);

        int fare = store.calculateFare(req);
        Bill bill = new Bill(req, fare);
        Payment payment = new Payment(bill);

        System.out.println("Fare for request: " + fare);
        payment.pay();
    }
}
