package com.example.seatreservation;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewPartyController implements Initializable {

    public AnchorPane mainPanel;
    public TextField tfID;
    public ChoiceBox<String> cbSlot;
    public DatePicker dpDate;
    public TextField tfMovieSearch;
    public ListView<String> moviesList;
    public ChoiceBox<String> cbHalls;
    public Button saveBTN;
    int id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id=globals.createNewSeqID(globals.partiesIDs);
        tfID.setText(Integer.toString(id));
        globals.makeList(globals.moviesLinkedList,moviesList);
        ObservableList<String>movies=globals.makeObsList(globals.moviesLinkedList);
        tfMovieSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String filter = newValue.toLowerCase();
            moviesList.setItems(movies.filtered(movie -> movie.toLowerCase().contains(filter)));
        });


    }
    Movie movie=null;
    public void selectMovie(){
        for (Movie movie1:globals.moviesLinkedList){
            if (Objects.equals(movie1.getMovieName(), moviesList.getSelectionModel().getSelectedItem())){
                movie=movie1;
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmed");
                alert.setContentText("Movie "+movie1.getMovieName()+" selected.");
                alert.showAndWait();
                return;
            }
        }
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Invalid selection");
        alert.showAndWait();
    }
    public void save(){
        Party party=new Party();
        party.setID(id);
        party.setDate(dpDate.getValue());
        if(movie!=null){
            party.setMovie(movie);
        }

    }
}
