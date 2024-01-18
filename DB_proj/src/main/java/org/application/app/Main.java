package org.application.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        try (Connection connection = databaseConnection.getConnection();) {

            System.out.println("Connected to the database!");

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/application/login/login-page.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        primaryStage.setTitle("Bazy danych - projekt");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
