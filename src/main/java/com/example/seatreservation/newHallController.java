package com.example.seatreservation;
import com.gluonhq.charm.glisten.control.TimePicker;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class newHallController implements Initializable {


    public TextField tfID;
    public Spinner<Integer> rowsSpinner;
    public Spinner<Integer> columnSpinner;
    public TextField tfName;
    public Spinner<Integer> FCNspinner;
    public TextField tfFCP;
    public Spinner<Integer> SCNspinner;
    public TextField tfSCP;
    public Spinner<Integer> TCNspinner;
    public TextField tfTCP;
    public AnchorPane mainPanel;
    public Button saveButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID.setEditable(false);
        int id=globals.createNewSeqID(globals.hallsIDs);
        tfID.setText(Integer.toString(id));
        mainPanel.setMaxSize(mainPanel.getPrefHeight(),mainPanel.getPrefWidth());
        rowsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30));
        columnSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30));
    }
    public void updateDisplay(){
        FCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(SCNspinner.getValue()+ TCNspinner.getValue())));
        SCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(FCNspinner.getValue()+ TCNspinner.getValue())));;
        TCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(SCNspinner.getValue()+ FCNspinner.getValue())));;
    }
    public void saveButtonC(){

    }
}
