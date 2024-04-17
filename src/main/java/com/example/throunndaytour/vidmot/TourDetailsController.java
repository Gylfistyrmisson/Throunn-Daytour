package com.example.throunndaytour.vidmot;
import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.users.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class TourDetailsController {
    @FXML
    private Label nameLabel, priceLabel, durationLabel, dateLabel, locationLabel;
    @FXML
    private Text descriptionText;
    private User user;

    @FXML
    private void onBokaClick(ActionEvent event) {
        System.out.println("Hæ");
    }

    public void setTourDetails(DayTour tour) {
        nameLabel.setText(tour.getName());
        descriptionText.setText(tour.getDescription());
        descriptionText.setWrappingWidth(350);
        priceLabel.setText("Price: $" + tour.getPrice());
        durationLabel.setText("Duration: " + tour.getDuration() + " hours");

        int[] dateArray = tour.getDate();
        dateLabel.setText(String.format("Date: %d/%d/%d", dateArray[0], dateArray[1], dateArray[2]));
        locationLabel.setText("Location: " + tour.getLocation());
    }
    public void initData(User user) {
        this.user = user;
    }
}

