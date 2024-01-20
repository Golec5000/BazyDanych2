package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.enums.Positions;
import org.application.intefaces.ControllerInterface;
import org.application.services.UserService;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeeAddUserPageController implements ControllerInterface {
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
    private ComboBox<Positions> positionBox;

    @FXML
    private Button registryButton;

    private final UserService userService = UserService.getInstance();

    public void back(ActionEvent actionEvent) {
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

    public void registry(ActionEvent actionEvent) {
        try {

            if (userService.isNickOccupiedInCustomers(loginTextField.getText()) || userService.isNickOccupiedInEmployees(loginTextField.getText())) {
                System.out.println("Nick is occupied");
                return;
            }

            LocalDate date = LocalDate.now();

            Employee employee = userService.addUserEmployee(loginTextField.getText(),
                    nameTextField.getText(),
                    lastNameTextField.getText(),
                    passwordTextField.getText(),
                    numberTextField.getText(),
                    emailTextField.getText(),
                    positionBox.getSelectionModel().getSelectedItem().toString(),
                    date);

            if (employee != null) {

                System.out.println("Employee added");

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
            } else {
                System.out.println("Error while adding customer");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
