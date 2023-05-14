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
    public Spinner<Integer>tfFCP;
    public Spinner<Integer> SCNspinner;
    public Spinner<Integer>tfSCP;
    public Spinner<Integer> TCNspinner;
    public Spinner<Integer> tfTCP;
    public AnchorPane mainPanel;
    public Button saveButton;
    public TextField tfSeats;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfID.setEditable(false);
        tfSeats.setEditable(false);
        int id=globals.createNewSeqID(globals.hallsIDs);
        tfID.setText(Integer.toString(id));
        mainPanel.setMaxSize(mainPanel.getPrefHeight(),mainPanel.getPrefWidth());
        rowsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30));
        columnSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,30));

    }
    public void updateDisplay(){
        FCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(SCNspinner.getValue()+ TCNspinner.getValue())));
        SCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(FCNspinner.getValue()+ TCNspinner.getValue())));
        TCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()-(SCNspinner.getValue()+ FCNspinner.getValue())));
        tfSeats.setText(Integer.toString(rowsSpinner.getValue()*columnSpinner.getValue()));
    }
    public void saveButtonC(){
        int id=Integer.parseInt(tfID.getText());
        int nr= rowsSpinner.getValue();
        int nc= columnSpinner.getValue();
        String hn=tfName.getText();
        int fr= FCNspinner.getValue(),sr=SCNspinner.getValue(),tr=TCNspinner.getValue();
        int fp=tfFCP.getValue(),sp=tfSCP.getValue(),tp=tfTCP.getValue();
        SeatingClasses sc1=new SeatingClasses(fr,fp),sc2=new SeatingClasses(sr,sp),sc3=new SeatingClasses(tr,tp);
        Hall hall=new Hall(id,hn,nr,nc,sc1,sc2,sc3);
        globals.hallsLinkedList.add(hall);
    }
    public void rsC(){
        updateDisplay();
    }
}
