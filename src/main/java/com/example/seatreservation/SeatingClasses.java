package com.example.seatreservation;

public class SeatingClasses {
    private int ID,numberOfRows,seatPricing;
    SeatingClasses(){}
    SeatingClasses(int id,int numberOfRows,int seatPricing){
        this.ID=id;
        this.seatPricing=seatPricing;
        this.numberOfRows=numberOfRows;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    @Override
    public String toString() {
        return "SeatingClasses" +
                "\n, ID=" + ID +
                "\n, numberOfRows=" + numberOfRows +
                "\n, seatPricing=" + seatPricing +
                '\n';
    }
}
