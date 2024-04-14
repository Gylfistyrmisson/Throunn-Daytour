package com.example.throunndaytour;
import com.example.throunndaytour.database.DatabaseDaytour;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DayTourApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        DatabaseDaytour.getConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(DayTourApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Day Tours");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}