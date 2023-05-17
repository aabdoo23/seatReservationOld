package com.example.seatreservation;

public class SeatingClasses {
    private int numberOfRows,seatPricing;
    SeatingClasses(){}
    SeatingClasses(int numberOfRows,int seatPricing){
        this.seatPricing=seatPricing;
        this.numberOfRows=numberOfRows;
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

    public int getNumberOfRows() {
        return numberOfRows;
    }
}
