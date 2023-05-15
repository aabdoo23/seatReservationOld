package com.example.seatreservation;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
        globals.makeList(globals.hallsLinkedList,hallsList);
        globals.makeList(globals.partyLinkedList,partiesList);
        globals.makeList(globals.moviesLinkedList,moviesList);
        globals.makeList(globals.userLinkedList,usersList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDisplay();
    }
    public void newHALL() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newHall.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("New hall");
        stage.setScene(scene);
        stage.show();
    }
    public void newMOVIE() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newMovie.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("New movie");
        stage.setScene(scene);
        stage.show();
    }
    public void newParty() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newParty.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("New party");
        stage.setScene(scene);
        stage.show();
    }
    public void newUser() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newUserView.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("New user");
        stage.setScene(scene);
        stage.show();
    }
}
