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

import java.io.IOException;


public class CustomerMainPageController {

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

    public void getOrdersList(ActionEvent actionEvent){
        System.out.println("getOrdersList");
        getNewScene("/org/application/customer/customer-orders-page.fxml", actionEvent);

    }

    public void getOpinionsList(ActionEvent actionEvent){
        System.out.println("getOpinionsList");
        getNewScene("/org/application/customer/customer-opinios-page.fxml", actionEvent);
    }

    public void logout(ActionEvent actionEvent){
        System.out.println("logout");
        getNewScene("/org/application/login/login-page.fxml", actionEvent);

    }

    public void getProducts(ActionEvent actionEvent){
        System.out.println("getProducts");
        getNewScene("/org/application/customer/customer-product-page.fxml", actionEvent);

    }

    public void editAccount(ActionEvent actionEvent){
        System.out.println("editAccount");
        getNewScene("/org/application/customer/customer-account-page.fxml", actionEvent);
    }

    public void setCustomerLabel(String customerLogin) {
        customerLabel.setText("Customer: " + customerLogin);
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


}
