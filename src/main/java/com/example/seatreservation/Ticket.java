package com.example.seatreservation;

import java.time.LocalTime;
import java.util.LinkedList;

public class Ticket {
    private int ID;
    private LocalTime issueTime;
    private double price;
    private User user;
    private Party party;
    private String seats;

    public Ticket(int reservationID, double price, User user, Party party) {
        this.ID= reservationID;
        this.issueTime=LocalTime.now();
        this.price=price;
        this.user = user;
        this.party=party;
        seats = "";
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    public void addToSeats(Seat seat) {
        this.seats+=(seat.toString());
    }

    public String  getSeats() {
        return seats;
    }


    public Ticket() {
        seats = "";
        this.setIssueTime(LocalTime.now());
    }

    public void setIssueTime(LocalTime issueTime) {
        this.issueTime = issueTime;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public LocalTime getIssueTime() {
        return issueTime;
    }

    public int getID() {
        return ID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setParty(Party party) {
        this.party = party;
    }
    public Party getParty() {
        return party;
    }

    @Override
    public String toString() {
        return "Ticket" +
                "\n, ID=" + ID +
                "\n, issueTime=" + issueTime +
                "\n, price=" + price +
                "\n, user=" + user.getName() +
                "\n, party=" + party +
                "\n, seats=" + seats +
                '\n';
    }
}