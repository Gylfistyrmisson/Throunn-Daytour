package com.example.throunndaytour.vidmot;
import com.example.throunndaytour.DayTourApplication;
import com.example.throunndaytour.controllers.BookingController;
import com.example.throunndaytour.controllers.DayTourController;
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

    public User user;

    /**
     * Athugar hvort email og password sé til í gagnagrunni.
     * Ef user er til, þá færumst við yfir á DayTour view, og User með.
     * @param event ActionEvent
     */
    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        User user = DatabaseDaytour.authenticateUser(email, password);

        if (user != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource("dayTours-view.fxml"));
                Parent root = fxmlLoader.load();

                DayTourController controller = fxmlLoader.getController();
                controller.initData(user);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

                actionTarget.setText("Login successful!");
            } catch (IOException e) {
                e.printStackTrace();
                actionTarget.setText("Failed to load the Day Tours view.");
            }
        } else {
            actionTarget.setText("Login failed. Invalid email or password.");
        }
    }

    /**
     * Opnar Register glugga.
     * @param actionEvent ActionEvent
     */
    public void handleRegisterButtonAction(ActionEvent actionEvent) {
        changeView("register-view.fxml");
    }

    /**
     * Hjálparfall sem skiptir um glugga.
     * @param fxml Strengur með heiti FXML skjals.
     */
    private void changeView(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource(fxml));
            Parent newView = fxmlLoader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();

            stage.setScene(new Scene(newView));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            actionTarget.setText("Error loading view: " + fxml);
        }
    }
}

