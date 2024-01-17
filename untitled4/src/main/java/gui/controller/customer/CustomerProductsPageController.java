package gui.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerProductsPageController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> categoryList;

    @FXML
    private TableColumn<?, ?> cena;

    @FXML
    private Button getdDescriptionButton;

    @FXML
    private Spinner<Integer> maxPrice;

    @FXML
    private Spinner<Integer> minPrice;

    @FXML
    private TableColumn<?, ?> nazwaProduktu;

    @FXML
    private TableColumn<?, ?> ocena;

    @FXML
    private Button orderProductButton;

    @FXML
    private TableView<?> productsTable;

    @FXML
    private TextField searchBar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void back(ActionEvent actionEvent) throws IOException {
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/customer-page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
