package com.example.throunndaytour.vidmot;

import com.example.throunndaytour.database.DatabaseDaytour;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class CreateTourController {
    @FXML
    public Label infoLabel;
       @FXML
    private DatePicker datePicker;
    @FXML
    private TextField locationField, descriptionField, durationField, nameField, priceField;

    /**
     * Upphafsstillir TextFields sem meiga bara taka við tölum.
     */
    @FXML
    public void initialize() {
        setupNumericField(priceField);
        setupNumericField(durationField);
    }
    /**
     * Setur upp TextField svo hann taki bara við tölum.
     */
    private void setupNumericField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    /**
     * Tekur við upplýsingum úr Create DayTour Formi
     * og býr til nýtt DayTour.
     */
    public void handleSubmit() {
        try {
            String name = nameField.getText();
            String description = descriptionField.getText();
            int price = Integer.parseInt(priceField.getText());
            int duration = Integer.parseInt(durationField.getText());
            LocalDate date = datePicker.getValue();
            String location = locationField.getText();

            if (date != null) {
                int[] dateArray = {date.getDayOfMonth(), date.getMonthValue(), date.getYear()};
                DatabaseDaytour.createDayTour(name, price, duration, dateArray, location, description);
                infoLabel.setText("Day tour created.");
            } else {
                infoLabel.setText("Date missing");
            }
        } catch (NumberFormatException e) {
            infoLabel.setText("Fill in all the fields.");
            System.out.println("Error parsing number: " + e.getMessage());
            e.printStackTrace();
        }
    }
}