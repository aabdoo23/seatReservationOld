package com.example.seatreservation;

import com.gluonhq.charm.glisten.control.TimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView tfPath;
    public Button selectPathBtn;
    int id=0;
    Image image;
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.JPG","*.jpeg","*.png","*.jpg"));
        String path= fileChooser.showOpenDialog(null).getAbsolutePath();
        Image image=new Image(path);
        tfPath.setImage(image);
    }
    public void saveButton(ActionEvent event) {
//        if (tfMovieName.getText().isEmpty() || tfScreenTime.getText().isEmpty() || tfPN.getText().isEmpty() || pw.isEmpty()) {
//            Alert alert=new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("Error: Please fill all information");
//            alert.showAndWait();
//            return;
//        }

        Movie movie=new Movie();
        movie.setID(id);
        movie.setMovieName(tfMovieName.getText());
        movie.setDescription(taDescription.getText());
        LocalTime lt=LocalTime.of(hrsSpinner.getValue(),minSpinner.getValue());
        movie.setPlayTime(lt);
        movie.setReleaseDate(dpReleaseDate.getValue());
        movie.setImg(image);
        globals.moviesLinkedList.add(movie);
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmed");
        alert.setContentText("Movie registered");
        alert.showAndWait();

    }
}
