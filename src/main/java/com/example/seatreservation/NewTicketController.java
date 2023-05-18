package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public Button selectBTN;
    public VBox seatsPane;
    public Spinner<Integer> numberOfSeatsSpinner;
    public Label lbMoney;
    public Button confirmBTN;
    Party selectedParty=null;
    int id=globals.createNewRandomID(globals.ticketsIDs);
    Movie movie=globals.movieForTicket;
    LinkedList<Party>parties=new LinkedList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        globals.spinnerTF(numberOfSeatsSpinner);
        if(selectedParty!=null) {
            int cnt=0;
            for (int i = 0; i < selectedParty.getHall().getRows(); i++) {
                for (int j = 0; j < selectedParty.getHall().getColumns(); j++) {
                    if (!selectedParty.getHall().getSeat(i, j).isBooked()) cnt++;
                }
            }
            numberOfSeatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, cnt));
        }
        globals.spinnerTF(numberOfSeatsSpinner);
        tfID.setText(Integer.toString(id));
        lbMovieName.setText(movie.getMovieName());
        ivPoster.setImage(movie.getImg());
        dpDate.setValue(LocalDate.now());
        updateDisplay();
        dpDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
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
    public void updateSeats(){ //selectBTN

        if(selectedParty==null)return;
        int[] x=new int[3];
        x[0]=selectedParty.getHall().getSeatingClass1().getNumberOfRows();
        x[1]=selectedParty.getHall().getSeatingClass2().getNumberOfRows();
        x[2]=selectedParty.getHall().getSeatingClass3().getNumberOfRows();
        int[] y=new int[3];
        y[1]=x[0];
        y[2]=x[1]+x[0];
        seatsPane.getChildren().clear();

        for (int k=0;k<3;k++){
            int z=k+1;
            seatsPane.getChildren().add(new Label("Seating class: "+(z)));
            for (int i=0;i<x[k];i++){
                HBox rowBox = new HBox();
                rowBox.setSpacing(5);
                for (int j=0;j<selectedParty.getHall().getColumns();j++){
                    int finalI = i+y[k];
                    int finalJ = j;
                    Button button=new Button();
                    button.setText(Integer.toString(j+1));
                    button.setDisable(selectedParty.getHall().getSeat(finalI,finalJ).isBooked());
                    if(selectedParty.getHall().getSeat(finalI,finalJ).isBooked())button.setStyle("-fx-background-color: #11ff00;");
                    button.setOnAction(e-> {
                        if (button.getStyle().isEmpty()){
                            if(selectSeat(finalI, finalJ,true))
                                button.setStyle("-fx-background-color: #11ff00;");
                        }
                        else{
                            button.setStyle("");
                            selectSeat(finalI, finalJ,false);
                        }
                    });
                    rowBox.getChildren().add(button);
                }
                seatsPane.getChildren().add(rowBox);
            }
            seatsPane.setSpacing(5);
        }
    }
    int seatCounter=0;
    LinkedList<Seat>pickedSeats=new LinkedList<>();
    public boolean selectSeat(int i, int j, boolean f) { //seat function
        if(seatCounter>= numberOfSeatsSpinner.getValue()&&f) {
            globals.showErrorAlert("Error: You have already chosen "+numberOfSeatsSpinner.getValue()+" seats");
            return false;
        }
        if(f) {
            seatCounter++;
            lbMoney.setText(Integer.toString(Integer.parseInt(lbMoney.getText())+selectedParty.getHall().getSeat(i,j).getSeatingClass().getSeatPricing()));
        }
        else {
            seatCounter--;
            lbMoney.setText(Integer.toString(Integer.parseInt(lbMoney.getText())-selectedParty.getHall().getSeat(i,j).getSeatingClass().getSeatPricing()));

        }
        Seat seat=(selectedParty.getHall().getSeat(i,j));
        seat.setBooked(f);
        for (Seat seat1:pickedSeats){//check if seat there
            if(seat1.getID()==seat.getID()){
                seat1.setBooked(f);
                return true;
            }
        }
        pickedSeats.add(seat);
        return true;
    }
    public void updateDisplay(){ //selectBTN //dp
        if(selectedParty!=null) { //calculate number of tickets to choose from
            int cnt=0;
            for (int i = 0; i < selectedParty.getHall().getRows(); i++) {
                for (int j = 0; j < selectedParty.getHall().getColumns(); j++) {
                    if (!selectedParty.getHall().getSeat(i, j).isBooked()) cnt++;
                }
            }
            numberOfSeatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, cnt));
        }

        parties=new LinkedList<>(); // build content of cbParties
        LocalDate date=dpDate.getValue();
        for (Party party:movie.getParties()){
            System.out.println(party.getSlot().toLocalDate().toString()+" "+date);
            if(party.getSlot().toLocalDate().toString().equals(date.toString())){
                parties.add(party);
            }
        }
        if(parties.size()!=0){
            globals.makeList(parties,cbParties);
            cbParties.getSelectionModel().selectFirst();
        }
        else{
            globals.showErrorAlert("No available parties for this date.");
        }
    }
    public void chooseParty(){ //selectBTN
        seatCounter=0;
        pickedSeats=new LinkedList<>();
        if(cbParties.getSelectionModel().getSelectedIndex()==-1||cbParties.getSelectionModel().isEmpty()) {

            globals.showErrorAlert("Selection invalid");
            return;
        }
        selectedParty=parties.get(cbParties.getSelectionModel().getSelectedIndex());
        updateSeats();
        updateDisplay();
        lbMoney.setText(Integer.toString(0));
    }

    public void save() throws IOException {//confirmBTN
        for (Seat seat:pickedSeats){
            globals.seatsLinkedList.remove(seat);
            selectedParty.getHall().markSeat(seat.getX(),seat.getY(),seat.isBooked());
            globals.seatsLinkedList.add(seat);
        }
        Ticket ticket=new Ticket();
        ticket.setID(globals.createNewRandomID(globals.ticketsIDs));
        ticket.setSeats(pickedSeats.toString());
        ticket.setParty(selectedParty);
        ticket.setUser(globals.signedInUser);
        ticket.setPrice(Integer.parseInt(lbMoney.getText()));
        ticket.setIssueTime(LocalTime.now());
        globals.currentTKT=ticket;
        globals.openNewForm("checkoutTicket.fxml","Payment");
        Stage s=(Stage)tfID.getParent().getScene().getWindow();
        s.close();
    }

}
