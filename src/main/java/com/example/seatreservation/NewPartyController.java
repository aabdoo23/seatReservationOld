package com.example.seatreservation;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static com.example.seatreservation.globals.prevParty;

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
    public Label movieLabel;
    int id;
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
    LinkedList<LocalDateTime> slots=new LinkedList<>();
    void updateDisplay(){
        Hall hall=globals.hallsLinkedList.get(cbHalls.getSelectionModel().getSelectedIndex());
        System.out.println(hall.toString());
        LocalDate date=dpDate.getValue();
        slots=new LinkedList<>();
        System.out.println(date.toString());
        for (LocalTime localTime:localTimes){
            LocalDateTime ltd=LocalDateTime.of(date,localTime);
            slots.add(ltd);
        }
        for (LocalTime lt:localTimes){
            LocalDateTime ltd= LocalDateTime.of(date,lt);
            if (hall.isSlotBooked(ltd)){
                slots.remove(ltd);
            }
        }
        globals.makeList(slots,cbSlot);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(prevParty==null) {
            id = globals.createNewSeqID(globals.partiesIDs);
            tfID.setText(Integer.toString(id));
            globals.makeList(globals.moviesLinkedList, moviesList);
            dpDate.setValue(LocalDate.now());
            ObservableList<String> movies = globals.makeObsList(globals.moviesLinkedList);
            tfMovieSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                String filter = newValue.toLowerCase();
                moviesList.setItems(movies.filtered(movie -> movie.toLowerCase().contains(filter)));
            });

            globals.makeList(globals.hallsLinkedList, cbHalls);
            cbHalls.getSelectionModel().selectFirst();
            cbSlot.getSelectionModel().selectFirst();
            updateDisplay();
            cbHalls.valueProperty().addListener((observable, oldValue, newValue) -> {
                updateDisplay();
            });
        }
        else{
            id = prevParty.getID();
            tfID.setText(Integer.toString(id));
            globals.makeList(globals.moviesLinkedList, moviesList);
            moviesList.getSelectionModel().select(prevParty.getMovie().toString());
            dpDate.setValue(prevParty.getSlot().toLocalDate());

            globals.makeList(globals.hallsLinkedList, cbHalls);
            cbHalls.getSelectionModel().select(prevParty.getHall().toString());
            cbSlot.getSelectionModel().select(prevParty.getSlot().toLocalTime().toString());
            tfMovieSearch.setText(prevParty.getMovie().toString());
            saveBTN.setDisable(true);
            tfMovieSearch.setDisable(true);
            selectBTN.setDisable(true);
            moviesList.setDisable(true);
            dpDate.setDisable(true);
        }

    }
    Movie movie=null;
    public void selectMovie(){
        updateDisplay();
        for (Movie movie1:globals.moviesLinkedList){
            if (Objects.equals(movie1.getMovieName(), moviesList.getSelectionModel().getSelectedItem())){
                movie=movie1;
                globals.showConfirmationAlert("Movie "+movie1.getMovieName()+" selected.");
                dpDate.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.isBefore(movie.getReleaseDate())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });
                return;
            }
        }
        globals.showErrorAlert("Invalid selection");
    }
    public void save(){
        Party party=new Party();
        party.setID(id);
        LocalDateTime ltd=slots.get(cbSlot.getSelectionModel().getSelectedIndex());
        party.setSlot(ltd);
        Hall hall=globals.hallsLinkedList.get(cbHalls.getSelectionModel().getSelectedIndex());
        party.setHall(hall);
        hall.markSlotAsBooked(ltd);
        if(movie!=null){
            party.setMovie(movie);
            movie.addToParties(party);
        }
        else {
            globals.showErrorAlert("Select a movie");
            return;
        }

        globals.partyLinkedList.add(party);

        globals.showConfirmationAlert("Party registered");
        Stage stage=(Stage) tfID.getScene().getWindow();
        stage.close();
    }
}
