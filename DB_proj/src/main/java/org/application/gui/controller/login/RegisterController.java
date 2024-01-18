package org.application.gui.controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    PasswordField addressTextField;

    @FXML
    Button backButton;

    @FXML
    PasswordField emailTextField;

    @FXML
    PasswordField lastNameTextField;

    @FXML
    TextField loginTextField;

    @FXML
    PasswordField nameTextField;

    @FXML
    PasswordField numberTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Button registryButton;


     public void back(ActionEvent actionEvent) {

         FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/login/login-page.fxml"));
         Parent root = null;
         try {
             root = loader.load();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

         Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();

    }

    // @todo save to database
    public void registry(ActionEvent actionEvent) {

    }

}

