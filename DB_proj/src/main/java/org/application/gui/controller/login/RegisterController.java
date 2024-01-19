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
import org.application.entity.Customer;
import org.application.services.UserService;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField addressTextField;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField numberTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button registryButton;

    private final UserService userService = UserService.getInstance();


    public void back(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/login/login-page.fxml"));
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

    public void registry(ActionEvent actionEvent) {

        try {
            Customer customer = userService.addUserCustomer(loginTextField.getText()
                    , nameTextField.getText()
                    , lastNameTextField.getText()
                    , addressTextField.getText()
                    , emailTextField.getText()
                    , numberTextField.getText()
                    , passwordTextField.getText());

            if(customer != null){

                System.out.println("Customer added");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/login/login-page.fxml"));
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
            else{
                System.out.println("Error while adding customer");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

