package com.example.seatreservation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Hall {
    private int ID;
    private int rows,columns;
    private String name;
    private SeatingClasses seatingClass1,seatingClass2,seatingClass3;
    private Map<LocalDateTime, Boolean> slots=new HashMap<>();
    public void markSlotAsBooked(LocalDateTime dateTime) {
        slots.put(dateTime, true);
    }

    public void markSlotAsAvailable(LocalDateTime dateTime) {
        slots.put(dateTime, false);
    }

    public boolean isSlotBooked(LocalDateTime dateTime) {
        return slots.getOrDefault(dateTime, false);
    }
    boolean[][] seats;

    Hall(int ID,String name,int rows,int columns,SeatingClasses sc1,SeatingClasses sc2,SeatingClasses sc3){
        this.ID=ID;
        this.name=name;
        this.rows=rows;
        this.columns=columns;
        this.seatingClass1=sc1;
        this.seatingClass2=sc2;
        this.seatingClass3=sc3;
        this.seats=new boolean[rows][columns];

    }
    public int getRows() {
        return rows;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void markSeat(int i,int j,boolean b){
        seats[i][j]=b;
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

    public boolean getSeat(int i,int j){
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
}
