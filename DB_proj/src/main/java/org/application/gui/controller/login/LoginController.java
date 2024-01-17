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
import org.application.intefaces.ControllerInterface;
import org.application.services.LoginService;

import java.io.IOException;


public class LoginController implements ControllerInterface {

    @FXML
    TextField loginTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Button loginButton;

    LoginService loginService = LoginService.getInstance();

    public void login(ActionEvent actionEvent){
        System.out.println("login");
        checkLogin(loginTextField.getText(), actionEvent);
    }

    private void checkLogin(String login, ActionEvent actionEvent) {
        System.out.println("checkLogin");


        if (loginService.userExists(login)) {

            System.out.println("customer");

            getNewScene("/org/application/customer/customer-page.fxml", actionEvent);
        } else {
            System.out.println("admin");

            getNewScene("/org/application/employee/employee-page.fxml", actionEvent);
        }
    }

    private void getNewScene(String fxmlPath, ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
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
}
