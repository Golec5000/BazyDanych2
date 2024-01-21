package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.util.List;

public class EmployeeOpinionsPageController implements ControllerInterface {

    @FXML
    private Button backButton;

    @FXML
    private TextArea opiniosArea;

    private Employee employee;

    private final ProductService productService = ProductService.getInstance();

    @FXML
    void back(ActionEvent actionEvent){
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

    public void loadOpinions(){

        try {
            List<Product> opinions = productService.getAllProducts();

            opinions.forEach(opinion -> {

                String sb = "Nazwa: " + opinion.getProductName() + "\n" +
                        "Opinia: " + opinion.getDescription() + "\n" + "\n";

                opiniosArea.appendText(sb);
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while loading opinions");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
    }

}

