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
import org.application.entity.Employee;
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeAddProductPageController implements ControllerInterface {

    @FXML
    Button addProductButton;

    @FXML
    Button backButton;

    @FXML
    TextArea descryptionArea;

    @FXML
    TextField categoryTextField;

    @FXML
    TextField productNameField;

    @FXML
    TextField productPriceField;

    private Employee employee;
    private final ProductService productService = ProductService.getInstance();



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
        try {
            productService.addProduct(new Product(productService.getLastID()
                    ,productNameField.getText()
                    ,Float.parseFloat(productPriceField.getText())
                    ,descryptionArea.getText()
                    ,categoryTextField.getText()));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            EmployeeProductsPageController employeeProductsPageController = loader.getController();
            employeeProductsPageController.setEmployeeLogin(employee);
            employeeProductsPageController.loadProducts();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;

    }


}
