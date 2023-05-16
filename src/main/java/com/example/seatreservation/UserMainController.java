package com.example.seatreservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {

    @FXML
    public TableView<Movie> tvMoviesTable=new TableView<>();
    @FXML
    public TableColumn<Movie, Image> posterCol=new TableColumn<>();
    @FXML
    public TableColumn<Movie, String> nameCol=new TableColumn<>();
    public Button bookButton;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        posterCol.setCellFactory(column -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);

                if (empty || image == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(100);
                    setGraphic(imageView);
                }
            }
        });
        posterCol.setCellValueFactory(new PropertyValueFactory<>("img"));


        nameCol.setCellValueFactory(new PropertyValueFactory<>("movieName"));

        tvMoviesTable.setItems(getMoviesList());
    }
    ObservableList<Movie> getMoviesList()
    {
        ObservableList<Movie>movies= FXCollections.observableArrayList();
        movies.addAll(globals.moviesLinkedList);
        return movies;
    }
}