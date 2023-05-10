package com.example.seatreservation;

public class Reservation {
    private int reservationID;
    private double price;
    private User user;
    private Hall hall;
    private Movie movie;

    public Reservation(int reservationID,double price, User user, Hall hall) {
        this.reservationID = reservationID;
        this.price=price;
        this.user = user;
        this.hall=hall;
    }

    public Reservation() {

    }


    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Hall getHall() {
        return hall;
    }
}