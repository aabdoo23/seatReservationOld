package com.example.seatreservation;

import java.util.LinkedList;
import java.util.Vector;

public class Hall {
    int ID;
    int rows,columns;
    LinkedList<SeatingClasses>seatingClasses;
    int[][] seats;

    Hall(int ID,int rows,int columns){
        this.ID=ID;
        this.rows=rows;
        this.columns=columns;
        this.seats=new int[rows][columns];
    }
    public int getRows() {
        return rows;
    }
    public void setSeats(int[][] seats) {
        this.seats = seats;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setSeatingClasses(LinkedList<SeatingClasses> seatingClasses) {
        this.seatingClasses = seatingClasses;
    }

    public int[][] getSeats() {
        return seats;
    }

    public LinkedList<SeatingClasses> getSeatingClasses() {
        return seatingClasses;
    }

    public int getID() {
        return ID;
    }

    public int getColumns() {
        return columns;
    }
}
