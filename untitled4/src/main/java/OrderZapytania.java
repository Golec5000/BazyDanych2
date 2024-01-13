import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderZapytania {

    // aktualizacja statusu zamowienia
    public boolean updateOrderStatus(int idZamowienia, String newStatus) throws SQLException {
        String query = "UPDATE Zamowienia SET statusZamowienia = ? WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, newStatus);
            pst.setInt(2, idZamowienia);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // usuwanie zamówienia
    public boolean deleteOrder(int idZamowienia) throws SQLException {
        String query = "DELETE FROM Zamowienia WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, idZamowienia);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // pobieranie wszystkich zamowien
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Zamowienia";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("idZamowienia"),
                        rs.getDate("dataZamowienia").toLocalDate(),
                        rs.getString("statusZamowienia"),
                        rs.getInt("idKlienta")
                ));
            }
        }
        return orders;
    }

    // pobieranie zwmowien po ID
    public Order getOrderById(int idZamowienia) throws SQLException {
        String query = "SELECT * FROM Zamowienia WHERE idZamowienia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idZamowienia);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getInt("idZamowienia"),
                        rs.getDate("dataZamowienia").toLocalDate(),
                        rs.getString("statusZamowienia"),
                        rs.getInt("idKlienta")
                );
            }
        }
        return null;
    }
}