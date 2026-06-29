package com.practice.MovieBookingSystem;

import java.time.LocalDateTime;
import java.util.List;

public class Show {
    int showId;
    Screen screen;
    Movie movie;
    MovieShowTime slot;   // broad slot (Morning, Evening, etc.)
    LocalDateTime startTime; // exact date/time

    public Show(int showId, Screen screen, Movie movie, MovieShowTime slot, LocalDateTime startTime) {
        this.showId = showId;
        this.screen = screen;
        this.movie = movie;
        this.slot = slot;
        this.startTime = startTime;
    }

//    public int getCostPerSeat() {
//        // cost is per seat, so just delegate to screen
//        return screen.getSeats().stream().mapToInt(Seat::getCostPerSeat).sum();
//        // or better: calculate based on seat location when booking
//    }

    public Booking bookSeats(User user, List<Seat> seats) {
        screen.reserveSeats(seats);
        return new Booking(showId, this, user, seats);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
