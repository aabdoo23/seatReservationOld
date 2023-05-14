package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.seatreservation.globals.spinnerTF;

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
        tfSCP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000));
        tfFCP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000));
        tfTCP.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000));

        spinnerTF(tfTCP);
        spinnerTF(tfSCP);
        spinnerTF(tfFCP);
        spinnerTF(rowsSpinner);
        spinnerTF(columnSpinner);
        spinnerTF(TCNspinner);
        spinnerTF(FCNspinner);
        spinnerTF(SCNspinner);


        initDisplay();
        rowsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
           updateDisplay();
        });
        columnSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
        FCNspinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
        SCNspinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
        TCNspinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateDisplay();
        });
    }


    public void initDisplay(){
        FCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()));
        SCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()));
        TCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, rowsSpinner.getValue()));
        tfSeats.setText(Integer.toString(rowsSpinner.getValue()*columnSpinner.getValue()));
    }
    public void updateDisplay(){
        int a=(rowsSpinner.getValue()-(SCNspinner.getValue()+ TCNspinner.getValue())),
                b=(rowsSpinner.getValue()-(FCNspinner.getValue()+ TCNspinner.getValue())),
                c=(rowsSpinner.getValue()-(FCNspinner.getValue()+ SCNspinner.getValue()));
        System.out.println(a+" "+b+" "+c+"\n");
        if (a>1){
            System.out.println(a);
            FCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, a));
        }
        if (b>1){
            System.out.println(b);
            SCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, b));
        }
        if (c>1){
            System.out.println(c);
            TCNspinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,c));
        }
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
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText("Hall registered");
        alert.showAndWait();
    }
}
