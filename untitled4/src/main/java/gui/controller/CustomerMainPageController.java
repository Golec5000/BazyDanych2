package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;




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
    }

    public void getOpinionsList(ActionEvent actionEvent){
        System.out.println("getOpinionsList");
    }

    public void logout(ActionEvent actionEvent){
        System.out.println("logout");
    }

    public void getProducts(ActionEvent actionEvent){
        System.out.println("getProducts");
    }

    public void editAccount(ActionEvent actionEvent){
        System.out.println("editAccount");
    }


}
