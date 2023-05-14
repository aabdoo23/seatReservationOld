package com.example.seatreservation;

import java.time.LocalTime;

public class Slot {
    private int number;
    private LocalTime time;
    private boolean filled;
    Slot(int n,LocalTime t,boolean f){
        this.number=n;
        this.time=t;
        this.filled=f;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public int getNumber() {
        return number;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
