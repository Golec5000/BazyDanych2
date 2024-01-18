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
import org.application.gui.controller.customer.CustomerMainPageController;
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

    @FXML
    Button registryButton;

    private final LoginService loginService = LoginService.getInstance();


    public void login(ActionEvent actionEvent) {
        System.out.println("login");
        checkLogin(loginTextField.getText(), actionEvent);
    }

    private void checkLogin(String login, ActionEvent actionEvent) {
        System.out.println("checkLogin");


        if (loginService.userExists(login)) {

            System.out.println("customer");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-page.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            CustomerMainPageController customerMainPageController = loader.getController();
            customerMainPageController.setCustomerLogin(login);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } else {
            System.out.println("admin");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
            Parent root;
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

    public void registry(ActionEvent actionEvent) {
        System.out.println("registry");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/login/register-page.fxml"));
        Parent root;
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
