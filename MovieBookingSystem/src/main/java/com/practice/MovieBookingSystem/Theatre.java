package com.practice.MovieBookingSystem;

import java.util.List;

public class Theatre {
    int theatreId;
    String name;
    List<Screen> screens;
    List<Show> shows; // all shows scheduled in this theatre

    public Theatre(int theatreId, String name, List<Screen> screens, List<Show> shows) {
        this.theatreId = theatreId;
        this.name = name;
        this.screens = screens;
        this.shows = shows;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public List<Show> getShows() {
        return shows;
    }

    // Find a show by movie name and slot
    public Show findShow(String movieName, MovieShowTime slot) {
        for (Show show : shows) {
            if (show.movie.movieName.equals(movieName) && show.slot == slot) {
                return show;
            }
        }
        return null;
    }
}
