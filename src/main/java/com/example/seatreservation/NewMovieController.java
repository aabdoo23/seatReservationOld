package com.example.seatreservation;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static com.example.seatreservation.globals.showErrorAlert;
import static com.example.seatreservation.globals.spinnerTF;

public class NewMovieController implements Initializable {

    public Button saveButton;
    public TextArea taDescription;
    public DatePicker dpReleaseDate;
    public Spinner<Integer> tfScreenTime;
    public TextField tfMovieName;
    public TextField tfID;
    public AnchorPane mainPanel;
    public ImageView tfPath;
    public Button selectPathBtn;
    int id=0;
    Image image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        hrsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23 ));
//        minSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59 ));
        id=globals.createNewRandomID(globals.moviesIDs);
        tfID.setText(Integer.toString(id));
        tfID.setEditable(false);
        spinnerTF(tfScreenTime);
        tfScreenTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200));
        dpReleaseDate.setValue(LocalDate.now());
    }
    public void selectIMG(){
        try {
            FileChooser fileChooser=new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.JPG","*.jpeg","*.png","*.jpg"));
            String path= fileChooser.showOpenDialog(null).getAbsolutePath();
            Image image=new Image(path);
            this.image=image;
            tfPath.setImage(image);
        }catch (Exception e){
            showErrorAlert("Please select a picture");
        }

    }
    public void saveButton() {
        if (tfMovieName.getText().isEmpty()  || tfPath==null || taDescription.getText().isEmpty()||dpReleaseDate.getValue()==null) {
            showErrorAlert("Error: Please fill all information");
            return;
        }

        Movie movie=new Movie();
        movie.setID(id);
        movie.setMovieName(tfMovieName.getText());
        movie.setDescription(taDescription.getText());
        movie.setScreenTime(tfScreenTime.getValue());
//        LocalTime lt=LocalTime.of(hrsSpinner.getValue(),minSpinner.getValue());
//        movie.setPlayTime(lt);
        movie.setReleaseDate(dpReleaseDate.getValue());
        movie.setImg(image);
        globals.moviesLinkedList.add(movie);
        globals.showConfirmationAlert("Movie registered");
        Stage stage=(Stage) tfID.getScene().getWindow();
        stage.close();

    }
}
