package org.application.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.application.services.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    @Override
    public void start(Stage primaryStage){

        try (Connection ignored = databaseConnection.getConnection()) {

            System.out.println("Connected to the database!");

            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/application/login/login-page.fxml")));
            } catch (IOException e) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Błąd ładowania pliku fxml");
                alert.setContentText("Sprawdź pliki aplikacji");
                alert.showAndWait();
            }
            Scene scene = new Scene(root);
            primaryStage.setTitle("Bazy danych - projekt");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText("Błąd połączenia z bazą danych");
            alert.setContentText("Sprawdź połączenie z bazą danych");
            alert.showAndWait();
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

}
