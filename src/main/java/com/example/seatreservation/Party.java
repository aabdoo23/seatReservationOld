package com.example.seatreservation;

public class Party {
    private int ID;
    private Slot slot;
    private Movie movie;
    private Hall hall;
    Party(){}
    Party(int id,Slot slot,Movie mv,Hall hl){
        this.slot=slot;
        this.hall=hl;
        this.ID=id;
        this.movie=mv;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getID() {
        return ID;
    }

    public Hall getHall() {
        return hall;
    }



    @Override
    public String toString() {
        return "Party" +
                "\n, ID=" + ID +
                "\n, slot=" + slot +
                "\n, movie=" + movie +
                "\n, hall=" + hall +
                '\n';
    }
}
