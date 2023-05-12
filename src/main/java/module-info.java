module com.example.seatreservation {
    requires javafx.controls;
    requires javafx.fxml;
    requires charm.glisten;


    opens com.example.seatreservation to javafx.fxml;
    exports com.example.seatreservation;
}