package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeAddProductPageController implements ControllerInterface {

    @FXML
    private Button addProductButton;

    @FXML
    private Button backButton;

    @FXML
    private TextArea descryptionArea;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

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
        EmployeeProductsPageController employeeProductsPageController = loader.getController();
        employeeProductsPageController.setEmployeeLogin(employee);


        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addProduct(ActionEvent actionEvent) {
        System.out.println("addProduct");
        try {

            String errorMessage = "";
            if (productNameField.getText().isBlank()) {
                errorMessage += "Nazwa produktu jest wymagana.\n";
            }
            if (productPriceField.getText().isBlank()) {
                errorMessage += "Cena produktu jest wymagana.\n";
            }
            if (descryptionArea.getText().isBlank()) {
                errorMessage += "Opis produktu jest wymagany.\n";
            }
            if (categoryTextField.getText().isBlank()) {
                errorMessage += "Kategoria produktu jest wymagana.\n";
            }

            if (!errorMessage.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd walidacji");
                alert.setHeaderText("Nie można dodać produktu");
                alert.setContentText(errorMessage);
                alert.showAndWait();
                return;
            }

            productService.addProduct(new Product(productService.getLastID()
                    ,productNameField.getText()
                    ,Float.parseFloat(productPriceField.getText())
                    ,descryptionArea.getText()
                    ,categoryTextField.getText()));

            back(actionEvent);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;

    }


}
