package org.application.gui.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.application.entity.Customer;
import org.application.intefaces.ControllerInterface;
import org.application.services.UserService;

import java.io.IOException;

public class CustomerAccountPageController implements ControllerInterface {

    @FXML
    Button backButton;

    @FXML
    Button comfirmButton;

    @FXML
    TextField loginTextFild;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

    private Customer customer;

    private final UserService userService = UserService.getInstance();

    public void back(ActionEvent actionEvent) {
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

    public void edit(ActionEvent actionEvent) {
        System.out.println("edit");

        customer = userService.updateUserCustomer(customer, loginTextFild.getText(), emailTextField.getText(), passwordTextField.getText());

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

}
