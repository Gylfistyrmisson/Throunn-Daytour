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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    public TextField nameField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField kennitalaField;
    @FXML
    public TextField passwordField;
    @FXML
    public Label messageField;
    @FXML
    public AnchorPane rootPane;

    public void handleStadfestaButtonAction(ActionEvent actionEvent) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String kennitala = kennitalaField.getText().trim();
        String password = passwordField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || kennitala.isEmpty() || password.isEmpty()) {
            messageField.setText("Fylla þarf í alla glugga");
            return;
        }

        try {
            User user = DatabaseDaytour.createUser(name, email, kennitala, password);
            if (user != null) {
                messageField.setText("Nýskráning tókst!");
                clearForm();
            } else {
                messageField.setText("Þetta netfang er nú þegar í notkun.");
            }
        } catch (RuntimeException e) {
            messageField.setText("Error during registration: " + e.getMessage());
        }
    }
    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        kennitalaField.setText("");
        passwordField.setText("");
    }


    public void handleLoginButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource("login-view.fxml"));
            Parent registerView = fxmlLoader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



