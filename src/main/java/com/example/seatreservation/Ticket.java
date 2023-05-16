package com.example.seatreservation;

import java.time.LocalTime;

public class Ticket {
    private int ID;
    private LocalTime issueTime;
    private double price;
    private User user;
    private Party party;

    public Ticket(int reservationID, double price, User user, Party party) {
        this.ID= reservationID;
        this.issueTime=LocalTime.now();
        this.price=price;
        this.user = user;
        this.party=party;
    }


    public Ticket() {

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
}