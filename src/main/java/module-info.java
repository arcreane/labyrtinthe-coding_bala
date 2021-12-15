module com.example.algomaze {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.algomaze to javafx.fxml;
    exports com.example.algomaze;
}