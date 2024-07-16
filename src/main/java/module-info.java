module com.ricomuh.supplierscrud {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.ricomuh.supplierscrud to javafx.fxml;
    exports com.ricomuh.supplierscrud;
}