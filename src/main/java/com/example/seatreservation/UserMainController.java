package com.example.seatreservation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserMainController implements Initializable {

    @FXML
    public TableView<Movie> tvMoviesTable=new TableView<>();
    @FXML
    public TableColumn<Movie, Image> posterCol=new TableColumn<>();
    @FXML
    public TableColumn<Movie, String> nameCol=new TableColumn<>();
    public Button bookButton;
    ObservableList<Movie>movies= FXCollections.observableArrayList();
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
        movies=FXCollections.observableArrayList();
        movies.addAll(globals.moviesLinkedList);
        return movies;
    }
    public void book() throws IOException {
        globals.movieForTicket=movies.get(tvMoviesTable.getSelectionModel().getSelectedIndex());
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("newTicket.fxml")));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("New ticket");
        stage.setScene(scene);
        stage.show();
    }
}