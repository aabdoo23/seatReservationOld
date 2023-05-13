package com.example.seatreservation;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Stack;

public class MainMenuController implements Initializable {
    public AnchorPane mainPanel;
    public TextField tfID;
    public Button loginButton;
    public PasswordField pfPW;
    public Button signUpButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void login(){
        int id=Integer.parseInt(tfID.getText());
        String pw=pfPW.getText();
        for (User user:globals.userLinkedList){
            if (user.getID()==id){
                if(Objects.equals(user.getPassword(), pw)){
//
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Invalid password");
                    alert.showAndWait();
                }
            }
        }
    }
    public void open() throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newUserView.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("new user");
        stage.setScene(scene);
        stage.show();
    }
}
