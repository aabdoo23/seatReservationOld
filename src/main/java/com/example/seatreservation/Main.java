package com.example.seatreservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    static AnchorPane root=new AnchorPane();
    static List<AnchorPane>grids=new ArrayList<>();
    private static int cur_idx=0;
//    Scene scene=new Scene(root);
    @Override
    public void start(Stage stage) throws IOException {
        grids.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml"))));

//        root.getChildren().add(grids.get(0));
        root=getPane(0);


//        root=getPane(1);
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public static AnchorPane getPane(int idx) {
        return grids.get(idx);
    }
    public static void setPane(int idx){
//        root=getPane(idx);
        root.getChildren().remove(grids.get(cur_idx));
        root.getChildren().add(grids.get(idx));
        cur_idx=idx;
    }

    public static void main(String[] args) {

        new DB();
        DB.getALL();
        launch();
        DB.setALL();
    }

}