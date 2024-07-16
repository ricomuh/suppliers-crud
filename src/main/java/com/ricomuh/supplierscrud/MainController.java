package com.ricomuh.supplierscrud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TableView<Supplier> tableView;

    @FXML
    TableColumn<Supplier, Integer> idColumn;

    @FXML
    TableColumn<Supplier, String> namaColumn;

    @FXML
    TableColumn<Supplier, String> alamatColumn;

    @FXML
    TableColumn<Supplier, String> telpColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        telpColumn.setCellValueFactory(new PropertyValueFactory<>("telp"));

        refreshTable();
    }

    void refreshTable() {
        tableView.setItems(Supplier.getSuppliers());
    }

    @FXML
    public void onAddButtonClick() {
        Supplier supplier = showAddDialog();
        if (supplier != null) {
            Supplier.addSupplier(supplier);
            refreshTable();
        }
    }

    @FXML
    public void onEditButtonClick() {
        Supplier supplier = tableView.getSelectionModel().getSelectedItem();
        if (supplier != null) {
            Supplier editedSupplier = showEditDialog(supplier);
            if (editedSupplier != null) {
                Supplier.updateSupplier(editedSupplier);
                refreshTable();
            }
        }
    }

    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        Supplier supplier = tableView.getSelectionModel().getSelectedItem();
        if (supplier != null) {
            Supplier.deleteSupplier(supplier);
            refreshTable();
        }
    }


    Supplier showAddDialog() {
        Dialog<Supplier> dialog = new Dialog<>();
        dialog.setTitle("Add Supplier");
        dialog.setHeaderText("Add Supplier");

        ButtonType saveButtonType = new ButtonType("Save");
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        TextField namaField = new TextField();
        namaField.setPromptText("Nama");
        TextField alamatField = new TextField();
        alamatField.setPromptText("Alamat");
        TextField telpField = new TextField();
        telpField.setPromptText("Telp");

        GridPane grid = new GridPane();
        grid.add(new Label("Nama:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("Alamat:"), 0, 1);
        grid.add(alamatField, 1, 1);
        grid.add(new Label("Telp:"), 0, 2);
        grid.add(telpField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Supplier(0, namaField.getText(), alamatField.getText(), telpField.getText());
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }

    Supplier showEditDialog(Supplier supplier) {
        Dialog<Supplier> dialog = new Dialog<>();
        dialog.setTitle("Edit Supplier");
        dialog.setHeaderText("Edit Supplier");

        ButtonType saveButtonType = new ButtonType("Save");
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        TextField namaField = new TextField(supplier.getNama());
        TextField alamatField = new TextField(supplier.getAlamat());
        TextField telpField = new TextField(supplier.getTelp());

        GridPane grid = new GridPane();
        grid.add(new Label("Nama:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("Alamat:"), 0, 1);
        grid.add(alamatField, 1, 1);
        grid.add(new Label("Telp:"), 0, 2);
        grid.add(telpField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Supplier(supplier.getId(), namaField.getText(), alamatField.getText(), telpField.getText());
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
