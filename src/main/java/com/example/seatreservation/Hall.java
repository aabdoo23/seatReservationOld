package com.example.seatreservation;

import java.time.LocalDateTime;
import java.util.*;

public class Hall {
    private int ID;
    private int rows,columns;
    private String name;
    private SeatingClasses seatingClass1,seatingClass2,seatingClass3;
    private Map<LocalDateTime, Boolean> slots=new HashMap<>();
    private Seat[][] seats=new Seat[1000][1000];

    public void markSlotAsBooked(LocalDateTime dateTime) {
        slots.put(dateTime, true);
    }

    public void markSlotAsAvailable(LocalDateTime dateTime) {
        slots.put(dateTime, false);
    }
    public boolean isSlotBooked(LocalDateTime dateTime) {
        return slots.getOrDefault(dateTime, false);
    }

    Hall(int ID,String name,int rows,int columns,SeatingClasses sc1,SeatingClasses sc2,SeatingClasses sc3){
        this.ID=ID;
        this.name=name;
        this.rows=rows;
        this.columns=columns;
        this.seatingClass1=sc1;
        this.seatingClass2=sc2;
        this.seatingClass3=sc3;
        this.seats=new Seat[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j]=new Seat(globals.createNewSeqID(globals.seatsIDs),i,j);
            }
        }
        for (int i = 0; i < seatingClass1.getNumberOfRows(); i++) {
            for (int j = 0; j < columns; j++) {
                seats[i][j].setSeatingClass(seatingClass1);
                globals.seatsLinkedList.add(seats[i][j]);
            }
        }
        for (int i = 0; i < seatingClass2.getNumberOfRows(); i++) {
            for (int j = 0; j < columns; j++) {
                seats[i+ seatingClass1.getNumberOfRows()][j].setSeatingClass(seatingClass2);
                globals.seatsLinkedList.add(seats[i+ seatingClass1.getNumberOfRows()][j]);
            }
        }for (int i = 0; i < seatingClass3.getNumberOfRows(); i++) {
            for (int j = 0; j < columns; j++) {
                seats[i+ seatingClass1.getNumberOfRows()+ seatingClass2.getNumberOfRows()][j].setSeatingClass(seatingClass3);
                globals.seatsLinkedList.add(seats[i+ seatingClass1.getNumberOfRows()+ seatingClass2.getNumberOfRows()][j]);
            }
        }

    }
    public int getRows() {
        return rows;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void markSeat(int i,int j,boolean b){
        seats[i][j].setBooked(b);
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

    public SeatingClasses getSeatingClass1() {
        return seatingClass1;
    }

    public SeatingClasses getSeatingClass2() {
        return seatingClass2;
    }

    public SeatingClasses getSeatingClass3() {
        return seatingClass3;
    }

    public void setSeatingClass1(SeatingClasses seatingClass1) {
        this.seatingClass1 = seatingClass1;
    }
    public void setSeatingClass2(SeatingClasses seatingClass2) {
        this.seatingClass2 = seatingClass2;
    }
    public void setSeatingClass3(SeatingClasses seatingClass3) {
        this.seatingClass3 = seatingClass3;
    }

    public Seat getSeat(int i,int j){
        return seats[i][j];
    }


    public int getID() {
        return ID;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return name ;
    }


//    @Override
//    public String toString() {
//        return "Hall" +
//                "\n, ID=" + ID +
//                "\n, rows=" + rows +
//                "\n, columns=" + columns +
//                "\n, name=" + name +
//                "\n, seatingClass1=" + seatingClass1 +
//                "\n, seatingClass2=" + seatingClass2 +
//                "\n, seatingClass3=" + seatingClass3 +
//                "\n, slots=" + slots +
//                "\n, seats=" + Arrays.toString(seats) +
//                '\n';
//    }
}
