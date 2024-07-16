module com.ricomuh.supplierscrud {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.ricomuh.supplierscrud to javafx.fxml;
    exports com.ricomuh.supplierscrud;
}