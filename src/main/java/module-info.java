module com.example.throunndaytour {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.throunndaytour to javafx.fxml;
    exports com.example.throunndaytour;
}