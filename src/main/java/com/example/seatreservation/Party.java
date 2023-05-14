package com.example.seatreservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Party {
    int ID;
    LocalTime time;
    LocalDate date;
    Movie movie;
    Hall hall;
    Party(){}
    Party(int id,LocalDate dt,LocalTime t,Movie mv,Hall hl){
        this.date=dt;
        this.hall=hl;
        this.ID=id;
        this.movie=mv;
        this.time=t;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getID() {
        return ID;
    }

    public Hall getHall() {
        return hall;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
