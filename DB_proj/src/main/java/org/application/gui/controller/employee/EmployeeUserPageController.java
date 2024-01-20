package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
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
    UserService userService=UserService.getInstance();

    public void back(ActionEvent event) {

    }

    public void setComboBox()
    {
        positionBox.setItems(FXCollections.observableArrayList(Positions.values()));
    }

    public void edit(ActionEvent event) {
    //userService.editEmployee(employee,new Employee(rzeczytusa));

    back(event);

    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
