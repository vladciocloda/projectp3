module com.example.projectp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;

    opens com.example.projectp3 to javafx.fxml;
    exports com.example.projectp3;
}