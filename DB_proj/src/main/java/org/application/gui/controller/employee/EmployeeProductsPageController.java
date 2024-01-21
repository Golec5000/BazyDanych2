package org.application.gui.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Employee;
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeProductsPageController implements ControllerInterface, Initializable {

    @FXML
    private Button addProductButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> categoryBox;

    @FXML
    private TableColumn<Product, Float> cena;

    @FXML
    private TableColumn<Product, String> kategoria;

    @FXML
    private TableColumn<Product, String> nazwa;

    @FXML
    private TableColumn<Product, String> ocena;

    @FXML
    private Button opiniosButton;

    @FXML
    private Button editProdutButton;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TextField searchBar;

    @FXML
    private Button deleteProduktButton;

    private final ProductService productService = ProductService.getInstance();
    private final ObservableList<Product> productsObservableList = FXCollections.observableArrayList();


    private Employee employee;

    public void back(ActionEvent actionEvent) {
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

    public void setBasicProperty() {
        List<String> kategorie = new ArrayList<>();
        try {
            kategorie.add("Kategorie");
            kategorie.addAll(productService.getKategorie());

            categoryBox.setItems(FXCollections.observableArrayList(kategorie));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getOpinions(ActionEvent actionEvent) {
        System.out.println("getOpinions");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-opinios-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeOpinionsPageController employeeOpinionsPageController = loader.getController();
        employeeOpinionsPageController.setEmployeeLogin(employee);
        employeeOpinionsPageController.loadOpinions();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addProduct(ActionEvent actionEvent) {
        System.out.println("addProduct");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-add-product-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeAddProductPageController employeeAddProductPageController = loader.getController();
        employeeAddProductPageController.setEmployeeLogin(employee);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadProducts() {

        try {
            List<Product> productServiceAllProducts = productService.getAllProducts();

            productsObservableList.addAll(productServiceAllProducts);

            nazwa.setCellValueFactory(new PropertyValueFactory<>("productName"));
            cena.setCellValueFactory(new PropertyValueFactory<>("price"));
            ocena.setCellValueFactory(new PropertyValueFactory<>("description"));
            kategoria.setCellValueFactory(new PropertyValueFactory<>("kategoria"));

            productTable.setItems(productsObservableList);

            FilteredList<Product> filteredData = new FilteredList<>(productsObservableList, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                String lowerCaseFilter = newValue.toLowerCase();
                filteredData.setPredicate(product -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null) return true;
                    return product.getProductName().toLowerCase().contains(lowerCaseFilter);
                });
            });

            categoryBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null || newValue.equals("Kategorie"))
                        return true;
                    return product.getKategoria().equals(newValue);
                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productTable.comparatorProperty());

            productTable.setItems(sortedData);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while getting products list");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void removeProduct(ActionEvent actionEvent) {
        System.out.println("removeProduct");

        Product product = productTable.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while removing product");
            alert.setContentText("You have to select product to remove");
            alert.showAndWait();
            return;
        }

        try {
            productService.deleteProduct(product.getProductId());
            productsObservableList.remove(product);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while removing product");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

        public void editProduct(ActionEvent actionEvent) {
        System.out.println("editProduct");

        Product product = productTable.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while editing product");
            alert.setContentText("You have to select product to edit");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-edit-product-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        EmployeeEditProductPageController employeeEditProductPageController = loader.getController();
        employeeEditProductPageController.setEmployeeLogin(employee);
        employeeEditProductPageController.setProduct(product);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }  public void setEmployeeLogin(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadProducts();
        setBasicProperty();
    }
}
