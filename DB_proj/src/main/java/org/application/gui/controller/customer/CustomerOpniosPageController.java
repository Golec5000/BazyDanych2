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
import org.application.intefaces.ControllerInterface;

import java.io.IOException;

public class CustomerOpniosPageController implements ControllerInterface {

    @FXML
    Button backButton;

    @FXML
    TextArea opiniosArea;

    private String customerLogin;

    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CustomerMainPageController customerPageController = loader.getController();
        customerPageController.setCustomerLogin(customerLogin);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }
}
