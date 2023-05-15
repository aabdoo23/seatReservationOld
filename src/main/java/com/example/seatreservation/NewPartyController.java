package com.example.seatreservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
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
    public Button selectBTN;
    int id;
    LocalTime lt=LocalTime.of(10,30);
    String[] strings={"10:30", "12:30", "14:30", "16:30", "18:30", "20:30","22:30","00:30"};
    LocalTime[] localTimes={
            LocalTime.of(10,30),
            LocalTime.of(12,30),
            LocalTime.of(14,30),
            LocalTime.of(16,30),
            LocalTime.of(18,30),
            LocalTime.of(20,30),
            LocalTime.of(22,30),
            LocalTime.of(0,30),
    };
    LinkedList<Slot>slotLinkedList=new LinkedList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id=globals.createNewSeqID(globals.partiesIDs);
        tfID.setText(Integer.toString(id));
        globals.makeList(globals.moviesLinkedList,moviesList);
        dpDate.setValue(LocalDate.now());
        ObservableList<String>movies=globals.makeObsList(globals.moviesLinkedList);
        tfMovieSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            String filter = newValue.toLowerCase();
            moviesList.setItems(movies.filtered(movie -> movie.toLowerCase().contains(filter)));
        });

        globals.makeList(globals.hallsLinkedList,cbHalls);
        cbHalls.getSelectionModel().selectFirst();

        cbHalls.valueProperty().addListener((observable, oldValue, newValue) -> {

            Hall hall=globals.hallsLinkedList.get(cbHalls.getSelectionModel().getSelectedIndex());
            System.out.println(hall.toString());
            LocalDate date=dpDate.getValue();
            slotLinkedList=new LinkedList<>();
            System.out.println(date.toString());
            for (LocalTime localTime:localTimes){
                Slot s=new Slot(localTime,date);
                slotLinkedList.add(s);
                System.out.println(s.toString());
            }
            for (LocalTime lt:localTimes){
                Slot slot1=new Slot(lt,date);
                for (Slot slot:hall.getSlots()){
                    if (slot==slot1){
                        slotLinkedList.remove(slot);
                        System.out.println(slot.toString());
                    }
                }
            }
            globals.makeList(slotLinkedList,cbSlot);
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
                dpDate.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.isBefore(movie.getReleaseDate())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // set disabled date style
                        }
                    }
                });
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
        party.setSlot(slotLinkedList.get(cbSlot.getSelectionModel().getSelectedIndex()));
        party.setHall(globals.hallsLinkedList.get(cbHalls.getSelectionModel().getSelectedIndex()));
        if(movie!=null){
            party.setMovie(movie);
            movie.addToParties(party);
        }
        globals.partyLinkedList.add(party);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Party registered");
        alert.showAndWait();
        Stage stage=(Stage) tfID.getScene().getWindow();
        stage.close();
    }
}
