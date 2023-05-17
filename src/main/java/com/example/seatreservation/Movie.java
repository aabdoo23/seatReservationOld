package com.example.seatreservation;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.LinkedList;

public class Movie {
    private int ID,screenTime;
    private String movieName,description;
    private Image img;
//    private LocalTime playTime;
    private LocalDate releaseDate;
    private LinkedList<Party>parties=new LinkedList<>();
    Movie(){}
    Movie(int id, int st, String mn, String dc, Image img, LocalDate rd){
        this.ID=id;
        this.screenTime=st;
        this.movieName=mn;
        this.description=dc;
        this.img=img;
        this.releaseDate=rd;
//        parties=new LinkedList<>();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LinkedList<Party> getParties() {
        return parties;
    }
    public void addToParties(Party party){
        this.parties.add(party);
    }
    public void removeFromParties(Party party){
        this.parties.remove(party);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public Image getImg() {
        return img;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setScreenTime(int screenTime) {
        this.screenTime = screenTime;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getID() {
        return ID;
    }

    public int getScreenTime() {
        return screenTime;
    }

    public String getMovieName() {
        return movieName;
    }

    @Override
    public String toString() {
        return movieName;
    }
}
