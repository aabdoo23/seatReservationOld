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
        ticket=globals.currentTKT; //assign ticket to work on as the chosen tkt
        tfID.setText(Integer.toString(ticket.getID()));//set tkt id
        tfIssueTime.setText(ticket.getIssueTime().toString());//set issue time as now
        tfName.setText(ticket.getUser().getName());//set username
        tfHallName.setText(ticket.getParty().getHall().getName()); //set hall name
        tfDateTime.setText(ticket.getParty().getSlot().getLtd().toString()); //set party time
        rbCash.selectedProperty().setValue(true);//select cash option first
        apCC.setDisable(true);//disable cc part
        tfPrice.setText(Double.toString(ticket.getPrice()));//set tkt price
        tfSeatsBooked.setText(ticket.getSeats().toString());//seats chosen
        dpExpDate.setValue(LocalDate.now()); //date preset to now
        rbCash.setOnAction(e->{//if clicked on rbCash
            rbCash.selectedProperty().setValue(!rbCash.isSelected()); //switch selection
            apCC.setDisable(rbCash.isSelected());//if selected cash disable cc part
        });
        rbCC.setOnAction(e -> {//if clicked on rbCC
            rbCC.selectedProperty().setValue(!rbCC.isSelected());//switch selection
        });
        if(ticket.getUser().getCard()==null){//if user has no card
            cbUseCC.setDisable(true);//disable cc part
        }
        tfMovieName.setText(ticket.getParty().getMovie().getMovieName());//movie name
        ivPoster.setImage(ticket.getParty().getMovie().getImg());//movie poster
        cbUseCC.setOnAction(event -> {//if clicked use user cc cb
            tfCN.setDisable(cbUseCC.isSelected());//disable taking info if selected and enable if not
            tfCHN.setDisable(cbUseCC.isSelected());//disable taking info if selected and enable if not
            dpExpDate.setDisable(cbUseCC.isSelected());//disable taking info if selected and enable if not
            pfCvv.setDisable(cbUseCC.isSelected());//disable taking info if selected and enable if not
            if(cbUseCC.isSelected()) {//if user wants to use their cc
                tfCN.setText(ticket.getUser().getCard().getCardNumber());//get info of user cc
                tfCHN.setText(ticket.getUser().getCard().getHolderName());//get info of user cc
                pfCvv.setText(Integer.toString(ticket.getUser().getCard().getCVV()));//get info of user cc
                dpExpDate.setValue(ticket.getUser().getCard().getExpDate());//get info of user cc
            }

        });

    }
    public void save(){
        if(rbCC.isSelected()&&(tfCN.getText().isEmpty()||tfCHN.getText().isEmpty()||pfCvv.getText().isEmpty())){//if user selected cc and cc data empty
            globals.showErrorAlert("Billing information missing");//error and return
            return;
        }
        globals.ticketsLinkedList.add(ticket);//add ticket total tickets
        ticket.getUser().addToTickets(ticket);//add ticket to user ticket
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
