package org.application.gui.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.application.intefaces.ControllerInterface;

import java.io.IOException;


public class CustomerMainPageController implements ControllerInterface {

    @FXML
    Label customerLabel;

    @FXML
    Label IDLabel;

    @FXML
    Button ordersListButton;

    @FXML
    Button opinionsListButton;

    @FXML
    Button loguotBotton;

    @FXML
    Button productsButton;

    @FXML
    Button accountEditButton;

    private String customerLogin = "";

    public void getOrdersList(ActionEvent actionEvent) {
        System.out.println("getOrdersList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-orders-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerOrdersPageController customerOrdersPageController = loader.getController();
        customerOrdersPageController.setCustomerLogin(customerLogin);
        customerOrdersPageController.loadOrders();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void getOpinionsList(ActionEvent actionEvent) {
        System.out.println("getOpinionsList");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-opinios-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerOpniosPageController customerOpniosPageController = loader.getController();
        customerOpniosPageController.setCustomerLogin(customerLogin);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent actionEvent) {
        System.out.println("logout");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/login/login-page.fxml"));
        Parent root = null;
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

    public void getProducts(ActionEvent actionEvent) {
        System.out.println("getProducts");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-product-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerProductsPageController customerProductsPageController = loader.getController();
        customerProductsPageController.setCustomerLogin(customerLogin);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void editAccount(ActionEvent actionEvent) {
        System.out.println("editAccount");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-account-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerAccountPageController customerAccountPageController = loader.getController();
        customerAccountPageController.setCustomerLogin(customerLogin);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void getNewScene(String fxmlPath, ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = null;
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


    public void setCustomerLogin(String login) {
        customerLabel.setText("Customer: " + login);
        this.customerLogin = login;
        System.out.println("customerLogin: " + customerLogin);
    }


}
