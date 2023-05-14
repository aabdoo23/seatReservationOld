package com.example.seatreservation;

import com.gluonhq.charm.glisten.control.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewMovieController implements Initializable {

    public Button saveButton;
    public TextArea taDescription;
    public DatePicker dpReleaseDate;
    public TextField tfScreenTime;
    public TextField tfMovieName;
    public TextField tfID;
    public AnchorPane mainPanel;
    public Spinner<Integer> hrsSpinner;
    public Spinner<Integer> minSpinner;
    public TextField tfPath;
    public Button selectPathBtn;
    int id=0;
    File file;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hrsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23 ));
        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59 ));
        id=globals.createNewRandomID(globals.moviesIDs);
        tfID.setText(Integer.toString(id));
        tfID.setEditable(false);
    }
    public void selectIMG(){
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg,*.jpeg,*.png,*.hvec"));
        file=fileChooser.showOpenDialog(null);
        tfPath.setText(file.getAbsolutePath());
    }
    public void saveButton(ActionEvent event) {
        Movie movie=new Movie();
        movie.setID(id);
        movie.setMovieName(tfMovieName.getText());
        movie.setDescription(taDescription.getText());
        LocalTime lt=LocalTime.of(hrsSpinner.getValue(),minSpinner.getValue());
        movie.setPlayTime(lt);
        movie.setReleaseDate(dpReleaseDate.getValue());
        movie.setImg(file);
        globals.moviesLinkedList.add(movie);

    }
}
