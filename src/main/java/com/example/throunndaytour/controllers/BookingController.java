package com.example.throunndaytour.controllers;

import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class BookingController {
    @FXML
    private Label nameLabel;


    // Bóka takkinn sendir DayTour inn hingað.
    public void initData(DayTour tour) {
        nameLabel.setText(tour.getName());
        // Getum sótt það sem við þurfum.
    }

}
