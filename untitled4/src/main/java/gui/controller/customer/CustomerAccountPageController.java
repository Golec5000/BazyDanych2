package gui.controller.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerAccountPageController {

    @FXML
    Button backButton;

    @FXML
    Button comfirmButton;

    @FXML
    TextField loginTextFild;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

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
