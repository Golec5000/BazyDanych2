package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.application.intefaces.ControllerInterface;

import java.io.IOException;

public class EmployeeAddProductPageController implements ControllerInterface {

    @FXML
    Button addProductButton;

    @FXML
    Button backButton;

    @FXML
    TextArea descryptionArea;

    @FXML
    ComboBox<?> productCategoryBox;

    @FXML
    TextField productNameField;

    @FXML
    TextField productPriceField;


    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
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

    public void addProduct(ActionEvent actionEvent) {
        System.out.println("addProduct");
    }



}
