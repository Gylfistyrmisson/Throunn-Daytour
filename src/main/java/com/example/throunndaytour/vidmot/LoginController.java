package com.example.throunndaytour.vidmot;
import com.example.throunndaytour.DayTourApplication;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;

import static com.example.throunndaytour.users.User.getEmail;

public class LoginController {
    @FXML
    public TextField emailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private Label actionTarget;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        //User user = DatabaseDaytour.getUser(1000);
        //assert user != null;
        //System.out.println(getEmail(user));
        boolean hallo = DatabaseDaytour.createUser("Haraldur", "halli@gmail.com", "190398", "password123");
        System.out.println(hallo);
    }

    @FXML
    public void handleSubmitButtonAction() {
        String email = emailField.getText();
        String password = passwordField.getText();
        User user = DatabaseDaytour.authenticateUser(email, password);

        if (user != null) {
            actionTarget.setText("Login successful!");
            changeView("dayTours-view.fxml");
        } else {
            actionTarget.setText("Login failed. Invalid email or password.");
        }
    }

    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        changeView("register-view.fxml");
    }

    private void changeView (String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource(fxml));
            Parent newView = fxmlLoader.load();
            Stage stage = (Stage) emailField.getScene().getWindow(); // Assuming emailField is not null and has been initialized by FXML loader
            stage.setScene(new Scene(newView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            actionTarget.setText("Error loading view: " + fxml);
        }
    }
}

