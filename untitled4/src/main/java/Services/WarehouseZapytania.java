package Services;

import Entity.Warehouse;
import Services.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseZapytania {

    //wyswietlanie dostepnych magazynow
    public List<Warehouse> getAllWarehouses() throws SQLException {
        List<Warehouse> warehouses = new ArrayList<>();
        String query = "SELECT * FROM ListaMagazynow";

        try (Connection conn = DatabaseConnection.getConnection();
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
