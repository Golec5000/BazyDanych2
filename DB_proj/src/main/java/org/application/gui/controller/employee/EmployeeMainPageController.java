package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.intefaces.ControllerInterface;

import java.io.IOException;


public class EmployeeMainPageController implements ControllerInterface {

    @FXML
    Label nameLabel;

    @FXML
    Label lastNameLabel;

    @FXML
    Label positionLabel;

    @FXML
    Label magazineLabel;

    @FXML
    Button productsListButton;

    @FXML
    Button ordersListButton;

    @FXML
    Button logoutButton;

    @FXML
    Button suppliersListButton;

    @FXML
    Button editButton;

    private Employee employee;

    public void getProductsList(ActionEvent actionEvent){
        System.out.println("getProductsList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeProductsPageController employeeProductPageController = loader.getController();
        employeeProductPageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void getOrdersList(ActionEvent actionEvent){
        System.out.println("getOrdersList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-orders-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeOrdersPageController employeeOrdersPageController = loader.getController();
        employeeOrdersPageController.setEmployee(employee);
        employeeOrdersPageController.loadOrders();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent actionEvent){
        System.out.println("logout");
        getNewScene("/org/application/login/login-page.fxml", actionEvent);
    }

    public void getSuppliersList(ActionEvent actionEvent){
        System.out.println("getSuppliersList");

        getNewScene("/org/application/employee/employee-delivers-page.fxml", actionEvent);
    }

    public void edit(ActionEvent actionEvent){
        System.out.println("remove");
    }

    private void getNewScene(String fxmlPath, ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
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

    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
        nameLabel.setText("Imie: " + employee.getName());
        lastNameLabel.setText("Nazwisko: " + employee.getLastName());
        positionLabel.setText("Stanowisko: " + employee.getPosition());
    }


}
