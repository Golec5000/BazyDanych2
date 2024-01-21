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
import org.application.enums.Positions;
import org.application.intefaces.ControllerInterface;

import java.io.IOException;


public class EmployeeMainPageController implements ControllerInterface {

    @FXML
    private Label nameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private Label magazineLabel;

    @FXML
    private Button productsListButton;

    @FXML
    private Button ordersListButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button suppliersListButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button editButton;

    private Employee employee;

    public void getProductsList(ActionEvent actionEvent) {
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
    public void setMagazineLabel(String Label)
    {
        magazineLabel.setText("Magazyn nr.: " + Label);
    }

    public void getOrdersList(ActionEvent actionEvent) {
        System.out.println("getOrdersList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-orders-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeOrdersPageController employeeOrdersPageController = loader.getController();
        employeeOrdersPageController.setEmployeeLogin(employee);
        employeeOrdersPageController.loadOrders();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent actionEvent) {
        System.out.println("logout");

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

    public void getSuppliersList(ActionEvent actionEvent) {
        System.out.println("getSuppliersList");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-delivers-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeDeliversPageController employeeDeliversPageController = loader.getController();
        employeeDeliversPageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void edit(ActionEvent actionEvent) {
        System.out.println("edit");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-edit-user-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeEditUsersPageController employeeEditUsersPageController = loader.getController();
        employeeEditUsersPageController.setEmployeeLogin(employee);
        employeeEditUsersPageController.setTable();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void addUser(ActionEvent actionEvent) {
        System.out.println("addUser");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-add-user-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeAddUserPageController employeeAddUserPageController = loader.getController();
        employeeAddUserPageController.setEmployeeLogin(employee);

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
        setBasicProperty(employee.getPosition());
    }

    public void setBasicProperty(Positions position){

        switch (position){
            case ADMIN -> {
                addUserButton.setVisible(true);
                editButton.setVisible(true);

                addUserButton.setDisable(false);
                editButton.setDisable(false);

                productsListButton.setLayoutX(49);
                productsListButton.setLayoutY(295);

            }
            case EMPLOYEE -> {
                addUserButton.setVisible(false);
                editButton.setVisible(false);

                addUserButton.setDisable(true);
                editButton.setDisable(true);

                productsListButton.setLayoutX(210);
                productsListButton.setLayoutY(297);
            }

        }


    }


}
