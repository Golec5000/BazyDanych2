import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) {
        MyGui myGui = new MyGui(primaryStage);
        myGui.showStage();
    }

    public static void main(String[] args) {
        launch(args);

    }
}