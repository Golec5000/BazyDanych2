package org.application.gui.controller.customer;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Order;
import org.application.entity.User;
import org.application.intefaces.ControllerInterface;
import org.application.services.OrderZapytania;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CustomerOrdersPageController implements ControllerInterface {

    @FXML
    Button backButton;

    @FXML
    TextField searchBar;

    @FXML
    TableView<Order> tableOrders;

    @FXML
    TableColumn<Order, LocalDate> tableDataZamowienia;

    @FXML
    TableColumn<Order, String> tableIdKlienta;

    @FXML
    TableColumn<Order, String> tableIdZamowienia;

    @FXML
    TableColumn<Order, String> tableStatusZamowienia;

    private User user;

    private final OrderZapytania orderZapytania = OrderZapytania.getInstance();

    private final ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();

    public void loadOrders() {
        System.out.println("customerLogin: " + user.getNick());

        try {
            List<Order> orders = orderZapytania.getOrdersByNick(user.getNick());

            ordersObservableList.addAll(orders);

            tableDataZamowienia.setCellValueFactory(new PropertyValueFactory<>("dataZamowienia"));
            tableIdKlienta.setCellValueFactory(new PropertyValueFactory<>("idKlienta"));
            tableIdZamowienia.setCellValueFactory(new PropertyValueFactory<>("idZamowienia"));
            tableStatusZamowienia.setCellValueFactory(new PropertyValueFactory<>("statusZamowienia"));

            tableOrders.setItems(ordersObservableList);

            FilteredList<Order> filteredData = new FilteredList<>(ordersObservableList, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(order -> {

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (order.getIdZamowienia().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (order.getIdKlienta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return order.getStatusZamowienia().toLowerCase().contains(lowerCaseFilter);

            }));

            SortedList<Order> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableOrders.comparatorProperty());

            tableOrders.setItems(sortedData);


        } catch (Exception e) {
            System.out.println("Error while getting orders list: " + e.getMessage());
        }
    }

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
        customerPageController.setCustomerLogin(user);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void setCustomerLogin(User user) {
        this.user = user;
    }
}
