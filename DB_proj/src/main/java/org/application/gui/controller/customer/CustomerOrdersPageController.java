package org.application.gui.controller.customer;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.application.entity.Order;
import org.application.intefaces.ControllerInterface;
import org.application.services.OrderZapytania;

import java.io.IOException;
import java.util.List;

public class CustomerOrdersPageController implements ControllerInterface {

    @FXML
    Button backButton;

    @FXML
    TextArea ordersListArea;

    private String customerLogin = "";

    private final OrderZapytania orderZapytania = OrderZapytania.getInstance();

    public void loadOrders() {
        System.out.println("customerLogin: " + customerLogin);

        try {
            List<Order> orders = orderZapytania.getOrdersByNick(customerLogin);

            StringBuilder ordersList = new StringBuilder();

            orders.forEach(order -> {
                ordersList.append(order.toString()).append("\n");
            });

            ordersListArea.setText(ordersList.toString());

        } catch (Exception e) {
            System.out.println("Error while getting orders list: " + e.getMessage());
        }
    }

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
