package com.practice.MovieBookingSystem;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Movie
        Movie movie = new Movie(1, "Inception", "English");

        // Seats
        Seat s1 = new Seat(101, SeatLocation.FRONT);
        Seat s2 = new Seat(102, SeatLocation.CENTER);
        List<Seat> seats = Arrays.asList(s1, s2);

        // Screen
        Screen screen = new Screen(1, Arrays.asList(s1, s2), movie);

        // Show
        Show show = new Show(1, screen, movie, MovieShowTime.EVENING, LocalDateTime.of(2026, 5, 23, 19, 30));

        // Theatre with one screen and one show
        Theatre theatre = new Theatre(1, "PVR Khairatabad", Arrays.asList(screen), Arrays.asList(show));

        // User
        User user = new User("Alice");

        // 🔑 Call findShow
        Show selectedShow = theatre.findShow("Inception", MovieShowTime.EVENING);

        if (selectedShow != null) {
            Booking booking = selectedShow.bookSeats(user, seats);
            int fare = booking.calculateFare();
            System.out.println("Fare: " + fare);

            Payment payment = new Payment(booking);
            payment.pay();

            System.out.println("Booking confirmed for " + user.name + " at " + selectedShow.getStartTime());
        } else {
            System.out.println("No show found for requested movie and time.");
        }
    }
}
