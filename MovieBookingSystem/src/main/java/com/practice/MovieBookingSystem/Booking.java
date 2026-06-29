package com.practice.MovieBookingSystem;

import java.util.List;

public class Booking {
    int bookingid;
    Show show;
    User user;
    List<Seat> seats;
    int noofseats;
    BookingStatus status;

    public Booking(int bookingid, Show show, User user, List<Seat> seats) {
        this.bookingid = bookingid;
        this.show = show;
        this.user = user;
        this.seats = seats;
        this.noofseats = seats.size();
        this.status = BookingStatus.RESERVED;
    }

    int calculateFare() {
        return seats.stream().mapToInt(Seat::getCostPerSeat).sum();
    }

    void confirmPayment() {
        this.status = BookingStatus.BOOKED;
    }
}
