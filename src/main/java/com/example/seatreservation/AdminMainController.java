package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {
    public Button newHallBTN;
    public Button newPartyBTN;
    public Button newMovieBTN;
    public Button refreshBTN;
    public ListView<String> hallsList;
    public ListView<String> moviesList;
    public ListView<String> partiesList;
    public ListView<String> usersList;
    public Button newUserBTN;
    public void updateDisplay(){
        globals.makeList(globals.hallsLinkedList,hallsList);//all halls
        globals.makeList(globals.partyLinkedList,partiesList);//all parties
        globals.makeList(globals.moviesLinkedList,moviesList);//all movies
        globals.makeList(globals.userLinkedList,usersList);//all users
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplay();
    }
    public void newHALL() throws IOException {
        globals.openNewForm("newHall.fxml","New hall");//open new hall form
    }
    public void newMOVIE() throws IOException {
        globals.openNewForm("newMovie.fxml","New movie");//open new movie form
    }
    public void newParty() throws IOException {
        globals.openNewForm("newParty.fxml","New party");//open new party form

    }
    public void newUser() throws IOException {
        globals.openNewForm("newUserView.fxml","New user");//open new user form

    }
}
