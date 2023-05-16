package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class NewTicketController implements Initializable {

    public AnchorPane mainPanel;
    public TextField tfID;
    public AnchorPane moviePane;
    public Label lbMovieName;
    public ImageView ivPoster;
    public DatePicker dpDate;
    public ChoiceBox<String> cbParties;
    public Label lbScreenTime;
    public Button selectBTN;
    public Label lbPartySelected;
    public FlowPane seatsPane;
    public Spinner<Integer> numberOfSeatsSpinner;
    public Label lbMoney;
    public Button confirmBTN;
    int id=globals.createNewRandomID(globals.ticketsIDs);
    Movie movie=globals.movieForTicket;
    public void updateDisplay(){
        LinkedList<Party>parties=new LinkedList<>();
        LocalDate date=dpDate.getValue();
        for (Party party:movie.getParties()){
            if(party.getSlot().getLtd().toLocalDate()==date){
                parties.add(party);
            }
        }
        if(parties.size()!=0){
            globals.makeList(parties,cbParties);
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No available parties for this date.");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID.setText(Integer.toString(id));
        lbMovieName.setText(movie.getMovieName());
        ivPoster.setImage(movie.getImg());
        dpDate.setValue(LocalDate.now());
        dpDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }
}
