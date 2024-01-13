import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyGui {
    private Stage stage;

    public MyGui(Stage stage) {
        this.stage = stage;
    }

    public void showStage() {
        StackPane root = new StackPane();
        root.getChildren().add(new Label("chui!"));

        Scene scene = new Scene(root, 300, 250);
        stage.setTitle("chui");
        stage.setScene(scene);
        stage.show();
    }
}