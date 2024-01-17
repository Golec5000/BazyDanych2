package org.application.services;

import org.application.entity.Warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseZapytania {

    private static WarehouseZapytania instance;

    private WarehouseZapytania() {
    }

    public static WarehouseZapytania getInstance() {
        if (instance == null) instance = new WarehouseZapytania();
        return instance;
    }

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    //wyswietlanie dostepnych magazynow
    public List<Warehouse> getAllWarehouses() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String query = "SELECT * FROM ListaMagazynow";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("IdMagazynu");
                String address = resultSet.getString("Adres");
                int administratorId = resultSet.getInt("AdministratorId");

                Warehouse warehouse = new Warehouse(id, address, administratorId);
                warehouses.add(warehouse);
            }
        }
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }

        return warehouses;
    }
}