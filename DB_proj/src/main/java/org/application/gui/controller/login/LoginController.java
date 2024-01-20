package org.application.gui.controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.application.entity.Customer;
import org.application.entity.Employee;
import org.application.entity.User;
import org.application.gui.controller.customer.CustomerMainPageController;
import org.application.gui.controller.employee.EmployeeMainPageController;
import org.application.intefaces.ControllerInterface;
import org.application.services.LoginService;

import java.io.IOException;


public class LoginController implements ControllerInterface {

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registryButton;

    private final LoginService loginService = LoginService.getInstance();


    public void login(ActionEvent actionEvent) {
        System.out.println("login");
        checkLogin(loginTextField.getText(), passwordTextField.getText(), actionEvent);
    }

    private void checkLogin(String login, String password, ActionEvent actionEvent) {
        System.out.println("checkLogin");

        User user = loginService.userExistsCustomer(login, password);

        if (user != null) {

            System.out.println("customer");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-page.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            CustomerMainPageController customerMainPageController = loader.getController();
            customerMainPageController.setCustomerLogin((Customer) user);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


            return;
        }

        user = loginService.userExistsEmployee(login, password);

        if(user != null) {
            System.out.println("admin");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            EmployeeMainPageController employeeMainPageController = loader.getController();
            employeeMainPageController.setEmployeeLogin((Employee) user);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Błąd logowania");
        alert.setContentText("Niepoprawny login lub hasło");
        alert.showAndWait();

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
