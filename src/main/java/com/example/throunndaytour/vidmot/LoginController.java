package com.example.throunndaytour.vidmot;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;

import static com.example.throunndaytour.users.User.getEmail;

public class LoginController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        //User user = DatabaseDaytour.getUser(1000);
        //assert user != null;
        //System.out.println(getEmail(user));
        boolean hallo = DatabaseDaytour.createUser("Haraldur","halli@gmail.com","190398","password123");
        System.out.println(hallo);
    }
}

