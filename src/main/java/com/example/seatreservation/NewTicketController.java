package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
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
        if(selectedParty!=null) {
            int cnt=0;
            for (int i = 0; i < selectedParty.getHall().getRows(); i++) {
                for (int j = 0; j < selectedParty.getHall().getColumns(); j++) {
                    if (!selectedParty.getHall().getSeat(i, j)) cnt++;
                }
            }
            numberOfSeatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, cnt));
        }
        globals.spinnerTF(numberOfSeatsSpinner);
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
    public void updateSeats(){

        if(selectedParty==null)return;
        int[] x=new int[3];
        x[0]=selectedParty.getHall().getSeatingClass1().numberOfRows;
        x[1]=selectedParty.getHall().getSeatingClass2().numberOfRows;
        x[2]=selectedParty.getHall().getSeatingClass3().numberOfRows;
//        seatsPane=new VBox();
//        seatsPane.setStyle("layoutX:486.0 ; layoutY:40.0; prefHeight:554.0; prefWidth:459.0");
//        mainPanel.getChildren().add(seatsPane);
        for (int k=0;k<3;k++){
            int z=k+1;
            seatsPane.getChildren().add(new Label("Seating class: "+(z)));
            for (int i=0;i<x[k];i++){
                HBox rowBox = new HBox();
                rowBox.setSpacing(5);
                for (int j=0;j<selectedParty.getHall().getColumns();j++){
                    Button button=new Button();
                    button.setText(Integer.toString(j+1));
                    button.setDisable(selectedParty.getHall().getSeat(i,j));
                    if(selectedParty.getHall().getSeat(i,j))button.setStyle("-fx-background-color: #11ff00;");
                    int finalI = i;
                    int finalJ = j;
                    button.setOnAction(e-> {
                        if (Objects.equals(button.getStyle(), "-fx-background-color: #ff0000;")){
                            button.setStyle("-fx-background-color: #11ff00;");
                            selectSeat(finalI, finalJ,true);
                        }
                        else{
                            button.setStyle("-fx-background-color: #ff0000;");
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
    public void selectSeat(int i, int j, boolean f) {
        if(seatCounter>= numberOfSeatsSpinner.getValue()) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error: You have already chosen "+numberOfSeatsSpinner.getValue()+" seats");
            alert.showAndWait();
            return;
        }
        if(f) {
            seatCounter++;
        }
        else seatCounter--;
        selectedParty.getHall().markSeat(i,j,f);

    }
    public void updateDisplay(){
        if(selectedParty!=null) {
            int cnt=0;
            for (int i = 0; i < selectedParty.getHall().getRows(); i++) {
                for (int j = 0; j < selectedParty.getHall().getColumns(); j++) {
                    if (!selectedParty.getHall().getSeat(i, j)) cnt++;
                }
            }
            numberOfSeatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, cnt));
        }

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
    public void chooseParty(){
        selectedParty=parties.get(cbParties.getSelectionModel().getSelectedIndex());
        updateSeats();
        updateDisplay();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Party selected");
        alert.setContentText("Party selected");
        alert.showAndWait();
    }
}
