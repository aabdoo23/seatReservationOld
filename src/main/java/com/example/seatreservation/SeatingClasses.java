package com.example.seatreservation;

public class SeatingClasses {
    int numberOfRows,seatPricing;
    Hall hall;
    SeatingClasses(){}
    SeatingClasses(int numberOfRows,int seatPricing,Hall hall){
        this.hall=hall;
        this.seatPricing=seatPricing;
        this.numberOfRows=numberOfRows;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setSeatPricing(int seatPricing) {
        this.seatPricing = seatPricing;
    }

    public int getSeatPricing() {
        return seatPricing;
    }

    public Hall getHall() {
        return hall;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
