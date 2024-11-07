module com.example.emae {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.emae to javafx.fxml;
    exports com.example.emae;
}