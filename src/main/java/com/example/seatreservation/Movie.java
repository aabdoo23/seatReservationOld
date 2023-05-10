package com.example.seatreservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {
    int ID,screenTime;
    String movieName;
    LocalTime playTime;
    LocalDate releaseDate;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setPlayTime(LocalTime playTime) {
        this.playTime = playTime;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setScreenTime(int screenTime) {
        this.screenTime = screenTime;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalTime getPlayTime() {
        return playTime;
    }

    public int getID() {
        return ID;
    }

    public int getScreenTime() {
        return screenTime;
    }

    public String getMovieName() {
        return movieName;
    }
}
