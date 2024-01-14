package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

    @FXML
    TextField loginTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Button loginButton;

    public void login(ActionEvent actionEvent){

        System.out.println("login");

    }
}
