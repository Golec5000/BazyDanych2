package org.application.gui.controller.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.application.intefaces.ControllerInterface;
import org.application.services.ProductService;

import java.io.IOException;
import java.util.List;

public class EmployeeOpinionsPageController implements ControllerInterface {

    @FXML
    private Button backButton;

    @FXML
    private TextArea opiniosArea;

    private int productId;
    private ProductService productService = ProductService.getInstance();

    public void setProductId(int productId) {
        this.productId = productId;
        loadOpinions();
    }


    private void loadOpinions() {

        try {
            List<String> opinions = productService.getOpinionsByProductId(productId);

            opinions.forEach(opinion -> {
                opiniosArea.appendText(opinion);
            });

        } catch (Exception e) {
            opiniosArea.setText("Brak opinii");
        }

    }

    @FXML
    void back(ActionEvent actionEvent){
        System.out.println("back");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/application/employee/employee-product-page.fxml"));
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

