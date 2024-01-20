package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Customer;
import org.application.entity.Employee;
import org.application.entity.Order;
import org.application.entity.Product;
import org.application.services.OrderZapytania;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class EmployeeOrdersPageController {

    @FXML
    private Button backButton;

    @FXML
    private Button getdStatusButton;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, String> statusTable;

    @FXML
    private TableColumn<Product, String> produktTable;

    @FXML
    private TableColumn<Order, LocalDate> dateTable;

    @FXML
    private TableColumn<Customer, String> idCustomerTable;

    @FXML
    private TableColumn<Order, String> idOrderTable;

    @FXML
    private TextField searchBar;

    private Employee employee;
    private final OrderZapytania orderZapytania = OrderZapytania.getInstance();
    private final ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();

    public void back(ActionEvent actionEvent) {
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeMainPageController employeeMainPageController = loader.getController();
        employeeMainPageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadOrders() {

        try {
            List<Order> orders = orderZapytania.getAllOrders();

            ordersObservableList.addAll(orders);

            dateTable.setCellValueFactory(new PropertyValueFactory<>("dataZamowienia"));
            idCustomerTable.setCellValueFactory(new PropertyValueFactory<>("idKlienta"));
            idOrderTable.setCellValueFactory(new PropertyValueFactory<>("idZamowienia"));
            statusTable.setCellValueFactory(new PropertyValueFactory<>("statusZamowienia"));
            produktTable.setCellValueFactory(new PropertyValueFactory<>("produkt"));

            ordersTable.setItems(ordersObservableList);

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
                } else if (order.getStatusZamowienia().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else return order.getProdukt().toLowerCase().contains(lowerCaseFilter);

            }));

            SortedList<Order> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(ordersTable.comparatorProperty());

            ordersTable.setItems(sortedData);


        } catch (Exception e) {
            System.out.println("Error while getting orders list: " + e.getMessage());
        }
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void changeStatus(ActionEvent actionEvent){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-order-status-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeOrderStatusPageController employeeOrderStatusPageController = loader.getController();
        employeeOrderStatusPageController.setEmployee(employee);
        employeeOrderStatusPageController.setOrder(ordersTable.getSelectionModel().getSelectedItem());
        employeeOrderStatusPageController.SetComboBox();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
