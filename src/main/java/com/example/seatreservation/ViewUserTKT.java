package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ViewUserTKT implements Initializable {

    public ListView<String>  lvTickets;
    public User user = globals.signedInUser;


    public void updateDisplay(){
        LinkedList<Ticket>curr=new LinkedList<>();
        for (Ticket tkt:globals.ticketsLinkedList){
            if (tkt.getUser().getID()==user.getID()){
                curr.add(tkt);
            }
        }
        globals.makeList(curr,lvTickets);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplay();
    }
}
