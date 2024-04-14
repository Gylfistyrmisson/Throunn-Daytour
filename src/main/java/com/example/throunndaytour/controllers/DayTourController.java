package com.example.throunndaytour.controllers;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Date;

public class DayTourController {
    @FXML
    public ListView<DayTour> listViewDayTours;

    private ObservableList<DayTour> dayTours = FXCollections.observableArrayList();


    public static void addDayTour(DayTour dayTour) {
        DatabaseDaytour.createDayTour(dayTour);
    }

    @FXML
    public void initialize() {
        // Creating a mock DayTour for testing
        for (int i = 1; i <= 20; i++) {
            String tourName = "Tour " + i;
            int price = 100 + i; // Incrementing price for variety
            DayTour tour = new DayTour(i, tourName, price, 8, new Date(), "Reykjavik", i % 2 == 0);
            dayTours.add(tour);
        }

        listViewDayTours.setItems(dayTours);
        listViewDayTours.setCellFactory(new Callback<ListView<DayTour>, ListCell<DayTour>>() {
            @Override
            public ListCell<DayTour> call(ListView<DayTour> param) {
                return new ListCell<DayTour>() {
                    @Override
                    protected void updateItem(DayTour item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName() + " - $" + item.getPrice() + " for " + item.getDuration() + " hours");
                        }
                    }
                };
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
}
