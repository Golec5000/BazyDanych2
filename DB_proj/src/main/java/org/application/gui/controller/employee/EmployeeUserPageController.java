package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.enums.OrderStatus;
import org.application.enums.Positions;
import org.application.intefaces.ControllerInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.application.services.UserService;

import java.io.IOException;

public class EmployeeUserPageController implements ControllerInterface {

    private Employee employee;

    @FXML
    private Button backButton;

    @FXML
    private Button editButton;

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
    UserService userService = UserService.getInstance();
    Employee currEmployee;

    public void back(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-edit-user-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        EmployeeEditUsersPageController employeeEditUsersPageController = loader.getController();
        employeeEditUsersPageController.setEmployeeLogin(currEmployee);
        employeeEditUsersPageController.setTable();


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAllFields() {
        emailTextField.setText(employee.getEmail());
        lastNameTextField.setText(employee.getLastName());
        loginTextField.setText(employee.getNick());
        nameTextField.setText(employee.getName());
        numberTextField.setText(employee.getPhoneNumber());
        passwordTextField.setText(employee.getPassword());
    }

    public void setComboBox() {
        positionBox.setItems(FXCollections.observableArrayList(Positions.values()));
    }

    public void edit(ActionEvent event) {
        userService.updateUserEmployee(employee,loginTextField.getText(),nameTextField.getText(),lastNameTextField.getText(),emailTextField.getText(), numberTextField.getText(),passwordTextField.getText());
        back(event);

    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void getCurrEmployee(Employee outemployee){
        currEmployee=outemployee;
    }

}
