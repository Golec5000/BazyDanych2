package gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeProductsPageController {

    @FXML
    Button backButton;

    @FXML
    Button addProductButton;

    @FXML
    Button opiniosButton;

    @FXML
    Spinner<Integer> valueSpinner;

    @FXML
    ComboBox categoryBox;

    @FXML
    TextField searchBar;

    @FXML
    TableView productTable;

    public void back(ActionEvent actionEvent) throws IOException {
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/employee-page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
