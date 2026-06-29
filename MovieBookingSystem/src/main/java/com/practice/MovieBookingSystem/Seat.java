package com.practice.MovieBookingSystem;

public class Seat {
    int seatId;
    SeatLocation seatloc;
    boolean reserved;
    int costPerSeat;

    public Seat(int seatId, SeatLocation seatloc) {
        this.seatId = seatId;
        this.seatloc = seatloc;
        this.reserved = false;
        this.costPerSeat = defaultCost(seatloc);
    }

    private int defaultCost(SeatLocation loc) {
        switch (loc) {
            case FRONT: return 100;
            case CENTER: return 200;
            case BALCONY: return 300;
            default: return 150;
        }
    }

    public boolean isReserved() { return reserved; }
    public void reserve() { this.reserved = true; }
    public int getCostPerSeat() { return costPerSeat; }
}
