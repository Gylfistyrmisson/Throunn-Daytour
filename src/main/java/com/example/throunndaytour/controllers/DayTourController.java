package com.example.throunndaytour.controllers;
import com.example.throunndaytour.DayTourApplication;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class DayTourController {
    @FXML
    public ListView<DayTour> listViewDayTours;
    @FXML
    public TextField searchField;
    @FXML
    private ChoiceBox<String> sortChoiceBox;

    private ObservableList<DayTour> dayTours = FXCollections.observableArrayList();

/**
    public static void addDayTour(DayTour dayTour) {
        createDayTour(dayTour);


    public static void createDayTour(String Name,int Price,int Duration,int[] Date,String Location) {
        DatabaseDaytour.createDayTour(Name,Price,Duration,Date,Location);
    }

*/
    /**
     * Upphafstillir:
     * - Choice boxið
     * - Listann
     */
        @FXML
        public void initialize () {
            sortChoiceBox.setItems(FXCollections.observableArrayList(
                    "--Sort--",
                    "Price (High to Low)",
                    "Price (Low to High)",
                    "Duration (Long to Short)",
                    "Duration (Short to Long)"
            ));
            sortChoiceBox.setValue("--Sort--");
            sortChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    sortTours(newVal);
                }
            });
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

    /**
     * Sér um choice box sortingið
     * @param sortBy
     */
    private void sortTours(String sortBy) {
        Comparator<DayTour> comparator;
        switch (sortBy) {
            case "--Sort--":
                return;
            case "Price (High to Low)":
                comparator = Comparator.comparing(DayTour::getPrice).reversed();
                break;
            case "Price (Low to High)":
                comparator = Comparator.comparing(DayTour::getPrice);
                break;
            case "Duration (Long to Short)":
                comparator = Comparator.comparing(DayTour::getDuration).reversed();
                break;
            case "Duration (Short to Long)":
                comparator = Comparator.comparing(DayTour::getDuration);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
        FXCollections.sort(dayTours, comparator);
    }

    /**
     * Birtir valið Daytour úr listanum.
     * @param actionEvent
     */
    public void openDayTourHandler (ActionEvent actionEvent){
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            showTourDetails(selectedTour);
        }
    }

    /**
     * Býr til glugga með völdu DayTour.
     * @param tour DayTour
     */
    private void showTourDetails(DayTour tour) {
        Stage detailsStage = new Stage();
        detailsStage.initModality(Modality.APPLICATION_MODAL);
        detailsStage.setTitle("Tour Details");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        HBox nameBox = new HBox(new Label(tour.getName()));
        nameBox.setAlignment(Pos.CENTER);

        HBox priceBox = new HBox(new Label("Price: $" + tour.getPrice()));
        priceBox.setAlignment(Pos.CENTER);

        HBox durationBox = new HBox(new Label("Duration: " + tour.getDuration() + " hours"));
        durationBox.setAlignment(Pos.CENTER);

        int[] dateArray = tour.getDate();
        String formattedDate = String.format("%d/%d/%d", dateArray[0], dateArray[1], dateArray[2]);
        HBox dateBox = new HBox(new Label("Date: " + formattedDate));
        dateBox.setAlignment(Pos.CENTER);

        HBox locationBox = new HBox(new Label("Location: " + tour.getLocation()));
        locationBox.setAlignment(Pos.CENTER);

        Button bookingButton = new Button("Bóka");
        bookingButton.setOnAction(this::onBokaClick);  // Connect the button to the onBokaClick method
        HBox buttonBox = new HBox(bookingButton);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(nameBox, priceBox, durationBox, dateBox, locationBox, buttonBox);

        Scene scene = new Scene(layout, 400, 400); // You might want to adjust the size based on content
        detailsStage.setScene(scene);
        detailsStage.showAndWait();
    }

    /**
     * Leita takkinn
     * Tekur input úr searchField glugganum og leitar í gagnagrunni.
     * @param actionEvent ActionEvent
     */
    public void onLeitaHandler (ActionEvent actionEvent){
        String searchText = searchField.getText();
        loadDayTours(searchText);
    }

    /**
     * Leitar í gagnagrunn af DayTours út frá search String
     * og setur í lista.
     * @param search String
     */
    private void loadDayTours (String search){
        dayTours.clear();
        try {
            DayTour[] allDayTours = DatabaseDaytour.searchDayTour(search);
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

    /**
     * Bóka takkar:
     * Opnar nýtt view fyrir bókanir.
     * @param actionEvent ActionEvent
     */
    public void onBokaClick(ActionEvent actionEvent) {
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource("booking-view.fxml"));
                Parent registerView = fxmlLoader.load();

                BookingController controller = fxmlLoader.getController();
                controller.initData(selectedTour);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(registerView));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No tour selected");
        }
    }
}

