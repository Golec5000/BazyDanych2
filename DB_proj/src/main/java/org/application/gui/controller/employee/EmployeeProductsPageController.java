package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.application.intefaces.ControllerInterface;

import java.io.IOException;

public class EmployeeProductsPageController implements ControllerInterface {

    @FXML
    Button addProductButton;

    @FXML
    Button backButton;

    @FXML
    ComboBox<?> categoryBox;

    @FXML
    TableColumn<?, ?> cena;

    @FXML
    TableColumn<?, ?> id;

    @FXML
     TableColumn<?, ?> kategoria;

    @FXML
    TableColumn<?, ?> nazwa;

    @FXML
    TableColumn<?, ?> ocena;

    @FXML
    Button opiniosButton;

    @FXML
    TableView<?> productTable;

    @FXML
    TextField searchBar;

    @FXML
    TableColumn<?, ?> stan;

    @FXML
    Spinner<Integer> valueSpinner;

    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
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

    public void getOpinions(ActionEvent actionEvent){
        System.out.println("getOpinions");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-opinios-page.fxml"));
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

    public void addProduct(ActionEvent actionEvent){
        System.out.println("addProduct");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-add-product-page.fxml"));
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
}
