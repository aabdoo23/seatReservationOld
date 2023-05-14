package com.example.seatreservation;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class Hall {
    private int ID;
    private int rows,columns;
    private String name;
    private SeatingClasses seatingClass1,seatingClass2,seatingClass3;
    private final LinkedList<Slot> slots=new LinkedList<>();

    int[][] seats;

    Hall(int ID,String name,int rows,int columns,SeatingClasses sc1,SeatingClasses sc2,SeatingClasses sc3){
        this.ID=ID;
        this.name=name;
        this.rows=rows;
        this.columns=columns;
        this.seatingClass1=sc1;
        this.seatingClass2=sc2;
        this.seatingClass3=sc3;
        this.seats=new int[rows][columns];
        slots.add(new Slot(0, LocalTime.of(10,30),false));
        slots.add(new Slot(1, LocalTime.of(12,30),false));
        slots.add(new Slot(2, LocalTime.of(14,30),false));
        slots.add(new Slot(3, LocalTime.of(16,30),false));
        slots.add(new Slot(4, LocalTime.of(18,30),false));
        slots.add(new Slot(5, LocalTime.of(20,30),false));
        slots.add(new Slot(6, LocalTime.of(22,30),false));
        slots.add(new Slot(7, LocalTime.of(0,30),false));
    }
    public int getRows() {
        return rows;
    }

    public LinkedList<Slot> getSlots() {
        return slots;
    }

    public void bookSlot(int n){
        slots.get(n).setFilled(true);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int[][] getSeats() {
        return seats;
    }


    public int getID() {
        return ID;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return "Hall" +
                "\n, ID=" + ID +
                "\n, rows=" + rows +
                "\n, columns=" + columns +
                "\n, name=" + name ;
    }
}
