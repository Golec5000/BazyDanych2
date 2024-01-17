package gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeDeliversPageController {

    @FXML
    Button backButton;

    @FXML
    TableColumn<?, ?> deliverID;

    @FXML
    TableView<?> deliversTable;

    @FXML
    TableColumn<?, ?> name;

    @FXML
    TableColumn<?, ?> phoneNumber;


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

