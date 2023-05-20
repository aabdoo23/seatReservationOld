package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewUserTKT implements Initializable {

    public ListView<String>  lvTickets;
    public Button previewButton;
    public User user = globals.signedInUser;


    public void updateDisplay(){
        globals.makeList(user.getTickets(),lvTickets);
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplay();
    }
}
