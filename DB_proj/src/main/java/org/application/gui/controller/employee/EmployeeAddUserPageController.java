package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.application.entity.Employee;
import org.application.entity.Order;
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
    private Employee employee;

    public void initialize(){
        SetComboBox();
    }

    public void SetComboBox() {
        positionBox.setItems(FXCollections.observableArrayList(Positions.values()));
    }

    public void back(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EmployeeMainPageController employeePageController = loader.getController();
        employeePageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registry(ActionEvent actionEvent) {
        try {

            String errorMessage = "";

            if (loginTextField.getText().isBlank()) {
                errorMessage += "Pole login jest wymagane.\n";
            }
            if (nameTextField.getText().isBlank()) {
                errorMessage += "Pole imię jest wymagane.\n";
            }
            if (lastNameTextField.getText().isBlank()) {
                errorMessage += "Pole nazwisko jest wymagane.\n";
            }
            if (passwordTextField.getText().isBlank()) {
                errorMessage += "Pole hasło jest wymagane.\n";
            }
            if (numberTextField.getText().isBlank()) {
                errorMessage += "Pole numer telefonu jest wymagane.\n";
            }
            if (emailTextField.getText().isBlank()) {
                errorMessage += "Pole email jest wymagane.\n";
            }
            if (positionBox.getSelectionModel().isEmpty()) {
                errorMessage += "Musisz wybrać stanowisko.\n";
            }

            if (!errorMessage.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd walidacji");
                alert.setHeaderText("Nie można zarejestrować użytkownika");
                alert.setContentText(errorMessage);
                alert.showAndWait();
                return;
            }

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

    public void setEmployeeLogin(Employee employee) {this.employee = employee;}
}
