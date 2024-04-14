package com.example.throunndaytour.vidmot;

import com.example.throunndaytour.database.DatabaseDaytour;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        DatabaseDaytour.hallo();
    }
}

