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
    public Button previewHallBTN;
    public Button previewMovieBTN;
    public Button previewPartyBTN;
    public Button previewUserBTN;
    public ListView<String> ticketsList;
    public Button previewTKTbtn;

    public void updateDisplay(){
        globals.makeList(globals.hallsLinkedList,hallsList);//all halls
        globals.makeList(globals.partyLinkedList,partiesList);//all parties
        globals.makeList(globals.moviesLinkedList,moviesList);//all movies
        globals.makeList(globals.userLinkedList,usersList);//all users
        globals.makeList(globals.ticketsLinkedList,ticketsList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplay();
    }
    public void newHALL() throws IOException {
        globals.openNewForm("newHall.fxml","New hall");//open new hall form
        updateDisplay();
    }
    public void newMOVIE() throws IOException {
        globals.openNewForm("newMovie.fxml","New movie");//open new movie form
        updateDisplay();
    }
    public void newParty() throws IOException {
        globals.openNewForm("newParty.fxml","New party");//open new party form
        updateDisplay();
    }
    public void newUser() throws IOException {
        globals.openNewForm("newUserView.fxml","New user");//open new user form
        updateDisplay();
    }
    public void prevTKT() throws IOException {
        globals.currentTKT=globals.ticketsLinkedList.get(ticketsList.getSelectionModel().getSelectedIndex());
        globals.openNewForm("checkoutTicket.fxml","View ticket");//open new user form
        globals.currentTKT=null;
    }
    public void prevUser() throws IOException {
        globals.previewedUser=globals.userLinkedList.get(usersList.getSelectionModel().getSelectedIndex());
        globals.openNewForm("newUserView.fxml","View user");
        globals.previewedUser=null;
    }
    public void prevHall() throws IOException {
        globals.prevHall=globals.hallsLinkedList.get(hallsList.getSelectionModel().getSelectedIndex());
        globals.openNewForm("newHall.fxml","View Hall");
        globals.prevHall=null;
    }
    public void prevMovie() throws IOException {
        globals.previewedMovie=globals.moviesLinkedList.get(moviesList.getSelectionModel().getSelectedIndex());
        globals.openNewForm("newMovie.fxml","View Movie");
        globals.previewedMovie=null;
    }
    public void prevParty() throws IOException {
        globals.prevParty=globals.partyLinkedList.get(partiesList.getSelectionModel().getSelectedIndex());
        globals.openNewForm("newParty.fxml","View party");
        globals.prevParty=null;
    }
}
