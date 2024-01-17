package org.application.gui.controller.customer;


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
import org.application.entity.Product;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerProductsPageController implements Initializable, ControllerInterface {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> categoryList;

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
    private Button orderProductButton;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TextField searchBar;

    private final ProductService productService = ProductService.getInstance();

    ObservableList<Product> productsObservableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<Product> productServiceAllProducts = productService.getAllProducts();

            productsObservableList.addAll(productServiceAllProducts);

            nazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
            cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
            ocena.setCellValueFactory(new PropertyValueFactory<>("opis"));

            productsTable.setItems(productsObservableList);

            FilteredList<Product> filteredData = new FilteredList<>(productsObservableList, b -> true);

            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(product -> {

                    if (newValue.isBlank() || newValue.isEmpty() || newValue == null) return true;

                    String lowerCaseFilter = newValue.toLowerCase();

                    return product.getNazwaProduktu().toLowerCase().contains(lowerCaseFilter);

                });
            });

            SortedList<Product> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productsTable.comparatorProperty());

            productsTable.setItems(sortedData);

        } catch (Exception e) {
            System.out.println("Error while loading products" + e.getMessage());
        }


    }

    public void back(ActionEvent actionEvent) throws IOException {
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/customer/customer-page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
