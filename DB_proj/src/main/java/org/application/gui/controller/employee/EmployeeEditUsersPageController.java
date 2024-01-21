package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.intefaces.ControllerInterface;
import org.application.services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeEditUsersPageController implements ControllerInterface {

    private final UserService userService = UserService.getInstance();

    @FXML
    private TableColumn<Employee, String> NameTable;

    @FXML
    private Button backButton;

    @FXML
    private Button editEmployeeButton;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, String> lastNameTable;

    @FXML
    private TableColumn<Employee, String> nickTable;

    @FXML
    private TableColumn<Employee, String> positionTable;

    @FXML
    private TextField searchBar;

    private Employee employee;

    private final ObservableList<Employee> employeesObservableList = FXCollections.observableArrayList();


    public void back(ActionEvent event) {
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeMainPageController employeeMainPageController = loader.getController();
        employeeMainPageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void setTable()
    {
        ArrayList<Employee> employeeList = userService.getAllEmployees();
        employeesObservableList.setAll(employeeList);

        NameTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        nickTable.setCellValueFactory(new PropertyValueFactory<>("nick"));
        positionTable.setCellValueFactory(new PropertyValueFactory<>("position"));

        employeesTable.setItems(employeesObservableList);

    }
    public void editEmployee(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-order-status-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeUserPageController employeeUserPageController = loader.getController();
        employeeUserPageController.setEmployee(employeesTable.getSelectionModel().getSelectedItem());
        employeeUserPageController.setComboBox();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setEmployeeLogin(Employee employee)
    {
        this.employee = employee;
    }


}
