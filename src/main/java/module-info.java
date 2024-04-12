module com.example.throunndaytour {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.throunndaytour to javafx.fxml;
    exports com.example.throunndaytour;
    exports com.example.throunndaytour.controllers;
    opens com.example.throunndaytour.controllers to javafx.fxml;
    exports com.example.throunndaytour.vidmot;
    opens com.example.throunndaytour.vidmot to javafx.fxml;
}