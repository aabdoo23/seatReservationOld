package com.example.seatreservation;

public class Seat {
    private int ID;
    private boolean booked;
    int x,y;
    private SeatingClasses seatingClass;
    Seat(int x,int y){this.x=x;this.y=y;}
    Seat(int id,int i,int j,boolean booked,SeatingClasses classes){
        this.x=i;
        this.y=j;
        this.ID=id;
        this.booked=booked;
        this.seatingClass=classes;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    @Override
    public String toString() {
        return  ID+"";
    }
}
