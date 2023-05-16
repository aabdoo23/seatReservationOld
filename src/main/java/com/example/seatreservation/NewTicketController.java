package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

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
    public StackPane seatsPane;
    public Spinner<Integer> numberOfSeatsSpinner;
    public Label lbMoney;
    public Button confirmBTN;
    Party selectedParty=null;
    int id=globals.createNewRandomID(globals.ticketsIDs);
    Movie movie=globals.movieForTicket;
    LinkedList<Party>parties=new LinkedList<>();
    public void updateSeats(){
        if(selectedParty==null)return;
        for (int i=0;i<selectedParty.getHall().getSeatingClass1().numberOfRows;i++){
            HBox rowBox = new HBox();
            rowBox.setSpacing(5);
            for (int j=0;j<selectedParty.getHall().getColumns();j++){
                Button button=new Button();
                button.setDisable(selectedParty.getHall().getSeat(i,j));
                rowBox.getChildren().add(button);
            }
            seatsPane.getChildren().add(rowBox);
        }
    }
    public void updateDisplay(){
        parties=new LinkedList<>();
        LocalDate date=dpDate.getValue();
        for (Party party:movie.getParties()){
            System.out.println(party.getSlot().getLtd().toLocalDate().toString()+" "+date);
            if(party.getSlot().getLtd().toLocalDate().toString().equals(date.toString())){
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
        lbScreenTime.setText(Integer.toString(movie.getScreenTime()));
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
    public void chooseParty(){
        selectedParty=parties.get(cbParties.getSelectionModel().getSelectedIndex());
        updateSeats();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Party selected");
        alert.setContentText("Party selected");
        alert.showAndWait();
    }
}
