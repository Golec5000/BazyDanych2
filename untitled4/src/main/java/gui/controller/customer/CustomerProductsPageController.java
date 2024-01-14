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
import java.util.ResourceBundle;

public class CustomerProductsPageController implements Initializable {

    @FXML
    TableView productsTable;

    @FXML
    TextField searchBar;

    @FXML
    ComboBox categoryList;

    @FXML
    Spinner<Integer> minPrice;

    @FXML
    Spinner<Integer> maxPrice;

    @FXML
    Button getdDescriptionButton;

    @FXML
    Button orderProductButton;

    @FXML
    Button backButton;


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
