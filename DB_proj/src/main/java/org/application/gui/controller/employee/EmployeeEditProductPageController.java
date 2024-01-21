package org.application.gui.controller.employee;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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


        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //bo to by mozna bylo zrobic edycje w zaleznosci od podanego parametru ale to trzeba funkcje na stringbuildera przerobic i duzo zabawy wiec todo esa xD
    public void editProduct(ActionEvent actionEvent) {

        try{

            String errorMessage = "";
            if (extracted(errorMessage)) return;

            product.setProductName(productNameField.getText());
            product.setPrice(Float.parseFloat(productPriceField.getText()));
            product.setKategoria(categoryTextField.getText());
            product.setDescription(descryptionArea.getText());

            productService.updateProduct(product);


        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        back(actionEvent);

    }

    private boolean extracted(String errorMessage) {
        if (productNameField.getText().isBlank()) {
            errorMessage += "Nazwa produktu nie może być pusta.\n";
        }
        if (productPriceField.getText().isBlank()) {
            errorMessage += "Cena produktu nie może być pusta.\n";
        }
        if (categoryTextField.getText().isBlank()) {
            errorMessage += "Kategoria produktu nie może być pusta.\n";
        }
        if (descryptionArea.getText().isBlank()) {
            errorMessage += "Opis produktu nie może być pusty.\n";
        }

        if (!errorMessage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd walidacji");
            alert.setHeaderText("Nie można zaktualizować produktu");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return true;
        }
        return false;
    }


    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
