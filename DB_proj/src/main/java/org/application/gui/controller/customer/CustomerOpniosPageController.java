package org.application.gui.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.application.entity.Customer;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.util.List;

public class CustomerOpniosPageController implements ControllerInterface {

    @FXML
    private Button backButton;

    @FXML
    private TextArea opiniosArea;

    private Customer customer;

    private final ProductService productService = ProductService.getInstance();

    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerMainPageController customerPageController = loader.getController();
        customerPageController.setCustomerLogin(customer);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCustomerLogin(Customer customer) {
       this.customer = customer;
    }

    public void loadOpinions() {

        try {
            List<String> opinions = productService.getOpinionsByCustomerId(Integer.parseInt(customer.getKlientId()));

            opinions.forEach(opinion -> {
                opiniosArea.appendText(opinion);
            });

        } catch (Exception e) {
            opiniosArea.setText("Brak opinii");
        }

    }
}
