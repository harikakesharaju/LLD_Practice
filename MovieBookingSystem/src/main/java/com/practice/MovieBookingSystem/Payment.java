package com.practice.MovieBookingSystem;

public class Payment {
    Booking booking;

    public Payment(Booking booking) {
        this.booking = booking;
    }

    public void pay() {
        int amount = booking.calculateFare();
        booking.confirmPayment();
        System.out.println("Payment successful. Amount: " + amount);
    }
}
