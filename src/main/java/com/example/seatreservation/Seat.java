package com.example.seatreservation;

public class Seat {
    private int ID;
    private boolean booked;
    private SeatingClasses seatingClass;
    Seat(){}
    Seat(int id,boolean booked,SeatingClasses classes){
        this.ID=id;
        this.booked=booked;
        this.seatingClass=classes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setSeatingClass(SeatingClasses seatingClass) {
        this.seatingClass = seatingClass;
    }

    public int getID() {
        return ID;
    }

    public SeatingClasses getSeatingClass() {
        return seatingClass;
    }

    public boolean isBooked() {
        return booked;
    }
}
