package org.application.gui.controller.employee;

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
import org.application.intefaces.ControllerInterface;

import java.io.IOException;

public class EmployeeDeliversPageController implements ControllerInterface {

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


    public void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-page.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

