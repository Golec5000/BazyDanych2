package gui.controller.employee;

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
    Button logoutButton;

    @FXML
    Button suppliersListButton;

    @FXML
    Button removeButton;

    public void getProductsList(ActionEvent actionEvent){
        System.out.println("getProductsList");

        getNewScene("/employee-product-page.fxml", actionEvent);
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
