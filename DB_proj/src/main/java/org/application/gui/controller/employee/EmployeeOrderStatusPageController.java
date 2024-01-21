package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Customer;
import org.application.entity.Employee;
import org.application.entity.Order;
import org.application.entity.Product;
import org.application.enums.OrderStatus;
import org.application.intefaces.ControllerInterface;
import org.application.services.OrderZapytania;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeOrderStatusPageController implements ControllerInterface {

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
    private Button backButton;

    @FXML
    private Button getdStatusButton;

    @FXML
    private ComboBox<OrderStatus> statusBox;

    private final ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();
    private Order order;
    private Employee employee;

    OrderZapytania orderZapytania=OrderZapytania.getInstance();

    public void back(ActionEvent actionEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-orders-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeOrdersPageController employeeOrdersPageController = loader.getController();
        employeeOrdersPageController.setEmployeeLogin(employee);
        employeeOrdersPageController.loadOrders();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void SetComboBox() {
     statusBox.setItems(FXCollections.observableArrayList(OrderStatus.values()));
    }

    public void setOrder(Order order) {
        this.order = order;
        setOrdersTable(order);
    }
    private void setOrdersTable(Order order)
    {
        ordersObservableList.addAll(order);

        dateTable.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        idCustomerTable.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idOrderTable.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        statusTable.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        produktTable.setCellValueFactory(new PropertyValueFactory<>("product"));
        ordersTable.setItems(ordersObservableList);
    }
    public void changeStatus(ActionEvent actionEvent) {
        try {
            if (statusBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Nie wybrano statusu");
                alert.setContentText("Proszę wybrać status z listy rozwijanej.");
                alert.showAndWait();
                return;
            }
            orderZapytania.updateOrderStatus(ordersTable.getItems().getFirst().getOrderId(),statusBox.getValue().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        back(actionEvent);


    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
