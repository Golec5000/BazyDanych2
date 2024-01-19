package org.application.gui.controller.customer;


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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.application.entity.Product;
import org.application.entity.Customer;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerProductsPageController implements ControllerInterface {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> categoryList;

    @FXML
    private TableColumn<Product, Float> cena;

    @FXML
    private Button getdDescriptionButton;

    @FXML
    private Spinner<Integer> maxPrice;

    @FXML
    private Spinner<Integer> minPrice;

    @FXML
    private TableColumn<Product, String> nazwaProduktu;

    @FXML
    private TableColumn<Product, String> ocena;

    @FXML
    private TableColumn<Product, String> categoryTable;

    @FXML
    private Button orderProductButton;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TextField searchBar;

    private final ProductService productService = ProductService.getInstance();

    private final ObservableList<Product> productsObservableList = FXCollections.observableArrayList();

    private Customer customer;

    public void setKategorie() {
        ArrayList<String> kategorie=new ArrayList<>();
        try {
            kategorie.add("Kategorie");
            kategorie.addAll(productService.getKategorie());

            categoryList.setItems(FXCollections.observableArrayList(kategorie));

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        CustomerMainPageController customerMainPageController = loader.getController();
        customerMainPageController.setCustomerLogin(customer);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void oderProduct(ActionEvent actionEvent) {
        System.out.println("oderProduct");

        Product basket = productsTable.getSelectionModel().getSelectedItem();
        if (basket != null) {
            try {
                productService.order(basket, customer.getKlientId());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error while ordering product");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void setCustomerLogin(Customer customer) {
        this.customer = customer;
    }

    public void loadProducts() {

        try {
            List<Product> productServiceAllProducts = productService.getAllProducts();

            productsObservableList.addAll(productServiceAllProducts);

            nazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
            cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
            ocena.setCellValueFactory(new PropertyValueFactory<>("opis"));
            categoryTable.setCellValueFactory(new PropertyValueFactory<>("kategoria"));

            productsTable.setItems(productsObservableList);

            FilteredList<Product> filteredData = new FilteredList<>(productsObservableList, b -> true);


            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                String lowerCaseFilter = newValue.toLowerCase();
                filteredData.setPredicate(product -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null) return true;
                    return product.getNazwaProduktu().toLowerCase().contains(lowerCaseFilter);
                });
            });

            categoryList.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null || newValue.equals("Kategorie")) return true;
                    return product.getKategoria().equals(newValue);
                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productsTable.comparatorProperty());

            productsTable.setItems(sortedData);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while getting products list");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
