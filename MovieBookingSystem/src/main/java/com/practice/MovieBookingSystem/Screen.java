package com.practice.MovieBookingSystem;

import java.util.List;

public class Screen {
    int screenId;
    List<Seat> seats;
    Movie movie;

    public Screen(int screenId, List<Seat> seats, Movie movie) {
        this.screenId = screenId;
        this.seats = seats;
        this.movie = movie;
    }

    public List<Seat> getSeats() {
		return seats;
	}


	void reserveSeats(List<Seat> seatsToReserve) {
        for (Seat s : seatsToReserve) {
            s.reserve();
        }
    }
}
