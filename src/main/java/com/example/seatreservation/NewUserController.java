package com.example.seatreservation;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewUserController implements Initializable {
    @FXML
    public TextField tfID;
    @FXML
    public AnchorPane mainPanel;
    @FXML
    public TextField tfName;

    @FXML
    public TextField tfEmail=new TextField();
    @FXML
    public TextField tfPN=new TextField();
    @FXML
    public PasswordField pfPW1=new PasswordField();
    @FXML
    public PasswordField pfPW2=new PasswordField();
    @FXML
    public CheckBox cbCC=new CheckBox();
    @FXML
    public TextField tfCN=new TextField();
    @FXML
    public DatePicker dpExpDate=new DatePicker();
    @FXML
    public TextField tfCHN=new TextField();
    @FXML
    public PasswordField pfCvv=new PasswordField();
    @FXML
    public Button saveButton=new Button();
    int id=globals.createNewRandomID(globals.usersIDs);
    public void save(ActionEvent event) {
        String email = tfEmail.getText();
        String first = tfName.getText();
        String pn = tfPN.getText();
        String pw = pfPW1.getText();
        if (tfEmail.getText().isEmpty() || tfName.getText().isEmpty() || tfPN.getText().isEmpty() || pw.isEmpty()) {

            globals.showErrorAlert("Error: Please fill all information");
            return;
        }
        if (!Objects.equals(pfPW1.getText(), pfPW2.getText())) {

            globals.showErrorAlert("Error: Passwords don't match");

            return;
        }
        if(cbCC.isSelected()){
            if (tfCN.getText().isEmpty() ||tfCN.getText().length()!=16|| tfCHN.getText().isEmpty() || pfCvv.getText().isEmpty()) {
                globals.showErrorAlert("Error: Please fill all card information and check your info");
                return;
            }
        }
        User user = new User(id, first, email, pw, pn);
        if(cbCC.isSelected()){
            user.setCard(new CreditCard(globals.createNewRandomID(globals.ccIDs),tfCN.getText(),Integer.parseInt(pfCvv.getText()),dpExpDate.getValue(),tfCHN.getText()));
        }
        globals.userLinkedList.add(user);
        globals.showConfirmationAlert("User registered");
        Stage stage=(Stage) tfID.getScene().getWindow();
        stage.close();
    }
    public void clickCB(ActionEvent e) {
        tfCHN.setEditable(cbCC.isSelected());
        tfCN.setEditable(cbCC.isSelected());
        pfCvv.setEditable(cbCC.isSelected());
        dpExpDate.setEditable(cbCC.isSelected());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID.setEditable(false);
        tfID.setText(Integer.toString(id));
        tfCHN.setEditable(cbCC.isSelected());
        tfCN.setEditable(cbCC.isSelected());
        pfCvv.setEditable(cbCC.isSelected());
        dpExpDate.setEditable(cbCC.isSelected());
        dpExpDate.setValue(LocalDate.now());

    }
}
