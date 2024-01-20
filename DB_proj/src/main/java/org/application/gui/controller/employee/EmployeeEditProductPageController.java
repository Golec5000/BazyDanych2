package org.application.gui.controller.employee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.application.services.ProductService;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeEditProductPageController implements ControllerInterface {

    private Employee employee;
    private Product product;

    @FXML
    private Button backButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextArea descryptionArea;

    @FXML
    private Button editProductButton;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    private final ProductService productService = ProductService.getInstance();


    public void back(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeProductsPageController employeeProductsPageController = loader.getController();
        employeeProductsPageController.setEmployeeLogin(employee);
        employeeProductsPageController.setBasicProperty();
        employeeProductsPageController.loadProducts();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //@todo zabezpieczyć przed pustymi polami
    public void editProduct(ActionEvent actionEvent) {

        try{

            product.setNazwaProduktu(productNameField.getText());
            product.setCena(Float.parseFloat(productPriceField.getText()));
            product.setKategoria(categoryTextField.getText());
            product.setOpis(descryptionArea.getText());

            productService.updateProduct(product);


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeProductsPageController employeeProductsPageController = loader.getController();
        employeeProductsPageController.setEmployeeLogin(employee);
        employeeProductsPageController.setBasicProperty();
        employeeProductsPageController.loadProducts();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
