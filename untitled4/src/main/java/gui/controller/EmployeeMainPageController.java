package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class EmployeeMainPageController {

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
    Button categoriesListButton;

    @FXML
    Button loguotBotton;

    @FXML
    Button suppliersListButton;

    @FXML
    Button removeButton;

    public void getProductsList(ActionEvent actionEvent){
        System.out.println("getProductsList");
    }

    public void getOrdersList(ActionEvent actionEvent){
        System.out.println("getOrdersList");
    }

    public void getCategoriesList(ActionEvent actionEvent){
        System.out.println("getCategoriesList");
    }

    public void logout(ActionEvent actionEvent){
        System.out.println("logout");
    }

    public void getSuppliersList(ActionEvent actionEvent){
        System.out.println("getSuppliersList");
    }

    public void remove(ActionEvent actionEvent){
        System.out.println("remove");
    }


}
