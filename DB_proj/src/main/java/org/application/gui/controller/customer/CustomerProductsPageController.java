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
import javafx.util.converter.IntegerStringConverter;
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

    public void setBasicProperty() {
        List<String> kategorie = new ArrayList<>();
        try {
            kategorie.add("Kategorie");
            kategorie.addAll(productService.getKategorie());

            categoryList.setItems(FXCollections.observableArrayList(kategorie));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        minPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
        maxPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 10000));


        TextFormatter<Integer> minPriceFormatter = new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*")) {
                return change;
            }
            return null;
        });

        TextFormatter<Integer> maxPriceFormatter = new TextFormatter<>(new IntegerStringConverter(), 10000, change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?\\d*")) {
                return change;
            }
            return null;
        });

        minPrice.getEditor().setTextFormatter(minPriceFormatter);
        maxPrice.getEditor().setTextFormatter(maxPriceFormatter);
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

            nazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("productName"));
            cena.setCellValueFactory(new PropertyValueFactory<>("price"));
            ocena.setCellValueFactory(new PropertyValueFactory<>("description"));
            categoryTable.setCellValueFactory(new PropertyValueFactory<>("kategoria"));

            FilteredList<Product> filteredData = new FilteredList<>(productsObservableList, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> updateFilter(filteredData));
            categoryList.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> updateFilter(filteredData));
            minPrice.valueProperty().addListener((observable, oldValue, newValue) -> updateFilter(filteredData));
            maxPrice.valueProperty().addListener((observable, oldValue, newValue) -> updateFilter(filteredData));

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

    public void getDescription(ActionEvent actionEvent) {
        System.out.println("getDescription");

        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Description");
            alert.setHeaderText(product.getProductName());
            alert.setContentText(product.getDescription());
            alert.showAndWait();
        }
    }

    private void updateFilter(FilteredList<Product> filteredData) {
        String searchText = searchBar.getText();
        String selectedCategory = categoryList.getSelectionModel().getSelectedItem();

        Integer min = minPrice.getValue();
        Integer max = maxPrice.getValue();

        if (min > max) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while getting products list");
            alert.setContentText("Min price cannot be greater than max price");
            alert.showAndWait();

            minPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0));
            maxPrice.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 10000));

            return;
        }

        filteredData.setPredicate(product -> {
            if (product == null) {
                return false;
            }
            if (searchText != null && !searchText.isBlank() && !product.getProductName().toLowerCase().contains(searchText.toLowerCase())) {
                return false;
            }
            if (selectedCategory != null && !selectedCategory.isBlank() && !selectedCategory.equals("Kategorie") && !product.getKategoria().equals(selectedCategory)) {
                return false;
            }
            if (min != null && product.getPrice() < min) {
                return false;
            }
            if (max != null && product.getPrice() > max) {
                return false;
            }
            return true;
        });



    }


}
