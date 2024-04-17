package com.example.throunndaytour.controllers;
import com.example.throunndaytour.DayTourApplication;
import com.example.throunndaytour.database.DatabaseDaytour;
import com.example.throunndaytour.hlutir.DayTour;
import com.example.throunndaytour.users.User;
import com.example.throunndaytour.vidmot.TourDetailsController;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static com.example.throunndaytour.database.DatabaseDaytour.addBooking;

public class DayTourController {
    @FXML
    public ListView<DayTour> listViewDayTours;
    @FXML
    public TextField searchField;
    @FXML
    private Text actionText;
    @FXML
    private ChoiceBox<String> sortChoiceBox;

    private ObservableList<DayTour> dayTours = FXCollections.observableArrayList();

    private User user;

    @FXML
    private Label userName;

    /**
     * Upphafstillir:
     * - Choice boxið
     * - Listann
     */
    @FXML
    public void initialize() {

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
     *
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
     *
     * @param actionEvent
     */
    public void openDayTourHandler(ActionEvent actionEvent) {
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            showTourDetails(selectedTour);
        }
    }

    /**
     * Býr til glugga með völdu DayTour.
     *
     * @param tour DayTour
     */
    private void showTourDetails(DayTour tour) {
        try {
            FXMLLoader loader = new FXMLLoader(DayTourApplication.class.getResource("tourDetails-view.fxml"));
            VBox layout = loader.load();

            TourDetailsController controller = loader.getController();
            controller.setTourDetails(tour);
            controller.initData(user);

            Stage detailsStage = new Stage();
            detailsStage.initModality(Modality.APPLICATION_MODAL);
            detailsStage.setTitle("Tour Details");
            detailsStage.setScene(new Scene(layout, 400, 400));
            detailsStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Leita takkinn
     * Tekur input úr searchField glugganum og leitar í gagnagrunni.
     *
     * @param actionEvent ActionEvent
     */
    public void onLeitaHandler(ActionEvent actionEvent) {
        String searchText = searchField.getText();
        loadDayTours(searchText);
    }

    /**
     * Leitar í gagnagrunn af DayTours út frá search String
     * og setur í lista.
     *
     * @param search String
     */
    private void loadDayTours(String search) {
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
     *
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

    /**
     * Opnar nýjan Create DayTour glugga.
     *
     * @param actionEvent
     */
    public void onCreateHandler(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(DayTourApplication.class.getResource("createTour-view.fxml"));
            VBox layout = loader.load();

            Stage detailsStage = new Stage();
            detailsStage.initModality(Modality.APPLICATION_MODAL);
            detailsStage.setTitle("Búa til Day tour");
            detailsStage.setScene(new Scene(layout, 400, 400));
            detailsStage.showAndWait();
            loadDayTours("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initData(User user) {
        this.user = user;
        userName.setText(user.getName());
    }

    public void BokaButtonHandler(ActionEvent actionEvent) {
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            int daytourID = selectedTour.getId();
            int userId = user.getId();
            addBooking(daytourID, userId);
            actionText.setText("Bókun staðfest.");
        } else {
            actionText.setText("Veldu dayTour til að bóka.");
        }
    }

    /**
     * Eyðir völdu DayTour úr lista.
     * @param actionEvent ActionEvent
     */
    public void deleteButtonHandler(ActionEvent actionEvent) {
        DayTour selectedTour = listViewDayTours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            int dayTourId = selectedTour.getId();
            DatabaseDaytour.deleteDayTour(dayTourId);

            listViewDayTours.getItems().remove(selectedTour);
            actionText.setText("DayTour hefur verið eytt.");
        } else {
            actionText.setText("Veldu dayTour til að eyða.");
        }
    }
}
