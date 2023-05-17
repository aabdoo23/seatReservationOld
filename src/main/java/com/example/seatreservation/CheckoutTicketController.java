package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckoutTicketController implements Initializable {
    public TextField tfIssueTime;
    public TextField tfID;
    public TextField tfName;
    public ImageView ivPoster;
    public TextField tfMovieName;
    public TextField tfHallName;
    public TextField tfDateTime;
    public Button printTKTBTN;
    public RadioButton rbCash;
    public RadioButton rbCC;
    public TextField tfCN;
    public DatePicker dpExpDate;
    public TextField tfCHN;
    public PasswordField pfCvv;
    public CheckBox cbUseCC;
    public CheckBox cbRegisterAsCC;
    public Button saveBTN;
    public AnchorPane mainPanel;
    public AnchorPane apCC;
    public TextField tfSeatsBooked;
    public TextField tfPrice;
    Ticket ticket=new Ticket();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticket=globals.currentTKT;
        tfID.setText(Integer.toString(ticket.getID()));
        tfIssueTime.setText(ticket.getIssueTime().toString());
        tfName.setText(ticket.getUser().getName());
        tfHallName.setText(ticket.getParty().getHall().getName());
        tfDateTime.setText(ticket.getParty().getSlot().getLtd().toString());
        rbCash.selectedProperty().setValue(true);
        apCC.setDisable(true);
        tfPrice.setText(Double.toString(ticket.getPrice()));
        tfSeatsBooked.setText(ticket.getSeats().toString());
        dpExpDate.setValue(LocalDate.now());
        rbCash.setOnAction(e->{
            rbCash.selectedProperty().setValue(!rbCash.isSelected());
            apCC.setDisable(rbCash.isSelected());
        });
        rbCC.setOnAction(e -> {
            rbCC.selectedProperty().setValue(!rbCC.isSelected());
            apCC.setDisable(!rbCC.isSelected());
        });
        if(ticket.getUser().getCard()==null){
            cbUseCC.setDisable(true);
        }
        tfMovieName.setText(ticket.getParty().getMovie().getMovieName());
        ivPoster.setImage(ticket.getParty().getMovie().getImg());
        cbUseCC.setOnAction(event -> {
            tfCN.setDisable(cbUseCC.isSelected());
            tfCHN.setDisable(cbUseCC.isSelected());
            dpExpDate.setDisable(cbUseCC.isSelected());
            pfCvv.setDisable(cbUseCC.isSelected());
            if(cbUseCC.isSelected()) {

                tfCN.setText(ticket.getUser().getCard().getCardNumber());
                tfCHN.setText(ticket.getUser().getCard().getHolderName());
                pfCvv.setText(Integer.toString(ticket.getUser().getCard().getCVV()));
                dpExpDate.setValue(ticket.getUser().getCard().getExpDate());
            }

        });

    }
    public void save(){
        if(rbCC.isSelected()&&(tfCN.getText().isEmpty()||tfCHN.getText().isEmpty()||pfCvv.getText().isEmpty())){
            globals.showErrorAlert("Billing information missing");
            return;
        }
        globals.ticketsLinkedList.add(ticket);
        ticket.getUser().addToTickets(ticket);
        if(cbRegisterAsCC.isSelected()){
            ticket.getUser().setCard(new CreditCard(tfCN.getText(),Integer.parseInt(pfCvv.getText()),dpExpDate.getValue(),tfCHN.getText()));
        }
        globals.showConfirmationAlert("Ticket booked");
        Stage s=(Stage)tfID.getParent().getScene().getWindow();
        s.close();
    }
    public void printTKT(){

    }
}
