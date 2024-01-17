module org.application.db_proj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.application.app to javafx.fxml;
    exports org.application.app;

    exports org.application.gui.controller.login to javafx.fxml;
    opens org.application.gui.controller.login to javafx.fxml;

    exports org.application.gui.controller.customer to javafx.fxml;
    opens org.application.gui.controller.customer to javafx.fxml;

    exports org.application.gui.controller.employee to javafx.fxml;
    opens org.application.gui.controller.employee to javafx.fxml;
}