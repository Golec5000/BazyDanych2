package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.entity.Supplier;
import org.application.intefaces.ControllerInterface;
import org.application.services.WarehouseZapytania;

import java.io.IOException;
import java.util.List;

public class EmployeeDeliversPageController implements ControllerInterface {
    @FXML
    private Label warehouseIdlabel;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> deliverID;

    @FXML
    private TableView<Supplier> deliversTable;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> phoneNumber;

    private Employee employee;

    @FXML
    public void initialize() {
        setupColumns();
        loadSuppliers();
    }

    private void setupColumns() {

        deliverID.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        name.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void loadSuppliers() {
        try {
            List<Supplier> suppliers = WarehouseZapytania.getInstance().getAllSuppliers();
            ObservableList<Supplier> observableSuppliers = FXCollections.observableArrayList(suppliers);
            deliversTable.setItems(observableSuppliers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeMainPageController employeePageController = loader.getController();
        employeePageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
        warehouseIdlabel.setText("Magazyn nr: 1");
        //xD
    }

}

