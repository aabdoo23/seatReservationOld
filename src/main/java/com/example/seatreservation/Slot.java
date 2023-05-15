package com.example.seatreservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class Slot {
    private LocalTime time;
    private LocalDate date;
    Slot(LocalTime t,LocalDate date){
        this.time=t;
        this.date=date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.valueOf(time)+" "+String.valueOf(date);
    }
}
