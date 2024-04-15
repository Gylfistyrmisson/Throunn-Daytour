package com.example.throunndaytour.controllers;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.Date;

import static com.example.throunndaytour.database.DatabaseDaytour.*;

public class DayTourController {
    @FXML
    public ListView<DayTour> listViewDayTours;
    @FXML
    public TextField searchField;
    @FXML
    private ChoiceBox<String> sortChoiceBox;

    private ObservableList<DayTour> dayTours = FXCollections.observableArrayList();


    public static void addDayTour(DayTour dayTour) {
        createDayTour(dayTour);
    }

    @FXML
    public void initialize() {

        try {
            DayTour[] allDayTours = DatabaseDaytour.searchDayTour("");
            if (allDayTours != null && allDayTours.length > 0) {
                dayTours.addAll(Arrays.asList(allDayTours));
                System.out.println("Tours added: " + dayTours.size());
            } else {
                System.out.println("No tours found or array is empty");
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch tours: " + e.getMessage());
            e.printStackTrace();
        }

        listViewDayTours.setItems(dayTours);
        listViewDayTours.setCellFactory(param -> new ListCell<DayTour>() {
            @Override
            protected void updateItem(DayTour item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - $" + item.getPrice() + " for " + item.getDuration() + " hours");
                }
            }
        });
    }


    public void openDayTourHandler(ActionEvent actionEvent) {
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            showTourDetails(selectedTour);
        }
    }

    private void showTourDetails(DayTour tour) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows until this one is closed
        detailsStage.setTitle("Tour Details");

        VBox layout = new VBox(10);
        layout.getChildren().add(new Label("Name: " + tour.getName()));
        layout.getChildren().add(new Label("Price: $" + tour.getPrice()));
        layout.getChildren().add(new Label("Duration: " + tour.getDuration() + " hours"));
        layout.getChildren().add(new Label("Date: " + tour.getDate().toString()));
        layout.getChildren().add(new Label("Location: " + tour.getLocation()));

        Scene scene = new Scene(layout, 300, 200);
        detailsStage.setScene(scene);
        detailsStage.showAndWait(); // Show and wait for it to be closed before returning to the main application

    }

    public void onLeitaHandler(ActionEvent actionEvent) {
        String searchText = searchField.getText(); // Get text from searchField
        loadDayTours(searchText); // Load tours based on search text
    }

    private void loadDayTours(String search) {
        dayTours.clear(); // Clear existing data
        try {
            DayTour[] allDayTours = DatabaseDaytour.searchDayTour(search); // Fetch tours with new search term
            if (allDayTours != null && allDayTours.length > 0) {
                dayTours.addAll(Arrays.asList(allDayTours));
                System.out.println("Tours added: " + dayTours.size());
            } else {
                System.out.println("No tours found with the search criteria");
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch tours: " + e.getMessage());
            e.printStackTrace();
        }

        listViewDayTours.setItems(dayTours);
        listViewDayTours.setCellFactory(param -> new ListCell<DayTour>() {
            @Override
            protected void updateItem(DayTour item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - $" + item.getPrice() + " for " + item.getDuration() + " hours");
                }
            }
        });
    }
}
