package com.example.seatreservation;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class Movie {
    int ID,screenTime;
    String movieName,description;
    File img;
    LocalTime playTime;
    LocalDate releaseDate;
    Movie(){}
    Movie(int id,int st,String mn,String dc,File img,LocalTime pt,LocalDate rd){
        this.ID=id;
        this.screenTime=st;
        this.movieName=mn;
        this.description=dc;
        this.img=img;
        this.playTime=pt;
        this.releaseDate=rd;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public File getImg() {
        return img;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setPlayTime(LocalTime playTime) {
        this.playTime = playTime;
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

    public LocalTime getPlayTime() {
        return playTime;
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
}
