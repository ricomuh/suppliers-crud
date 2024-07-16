package com.ricomuh.supplierscrud;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class Supplier {
    Integer id;
    String nama;
    String alamat;
    String telp;

    public Supplier(Integer id, String nama, String alamat, String telp) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telp = telp;
    }

    public static ObservableList<Supplier> getSuppliers() {
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.getConn();
            var pst = conn.prepareStatement("SELECT * FROM suppliers");
            var rs = pst.executeQuery()) {
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("id"), rs.getString("nama"), rs.getString("alamat"), rs.getString("telp")));
            }
            return suppliers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addSupplier(Supplier supplier) {
        try (Connection conn = DBConnection.getConn();
            var pst = conn.prepareStatement("INSERT INTO suppliers (nama, alamat, telp) VALUES (?, ?, ?)")) {
            pst.setString(1, supplier.getNama());
            pst.setString(2, supplier.getAlamat());
            pst.setString(3, supplier.getTelp());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteSupplier(Supplier supplier) {
        try (Connection conn = DBConnection.getConn();
            var pst = conn.prepareStatement("DELETE FROM suppliers WHERE id = ?")) {
            pst.setInt(1, supplier.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateSupplier(Supplier supplier) {
        try (Connection conn = DBConnection.getConn();
            var pst = conn.prepareStatement("UPDATE suppliers SET nama = ?, alamat = ?, telp = ? WHERE id = ?")) {
            pst.setString(1, supplier.getNama());
            pst.setString(2, supplier.getAlamat());
            pst.setString(3, supplier.getTelp());
            pst.setInt(4, supplier.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }
}
