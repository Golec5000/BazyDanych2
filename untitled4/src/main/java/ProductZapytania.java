import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductZapytania {

    // dodawanie nowego produktu do bazy danych
    public boolean addProduct(Product product) throws SQLException {
        String query = "INSERT INTO Produkty (NazwaProduktu, Cena, Opis, Kategoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, product.getNazwaProduktu());
            pst.setFloat(2, product.getCena());
            pst.setString(3, product.getOpis());
            pst.setString(4, product.getKategoria());

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // aktualizowanie istniejącego produktu
    public boolean updateProduct(Product product) throws SQLException {
        String query = "UPDATE Produkty SET NazwaProduktu = ?, Cena = ?, Opis = ?, Kategoria = ? WHERE IdProduktu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, product.getNazwaProduktu());
            pst.setFloat(2, product.getCena());
            pst.setString(3, product.getOpis());
            pst.setString(4, product.getKategoria());
            pst.setInt(5, product.getIdProduktu());

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // usuwanie produktu
    public boolean deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM Produkty WHERE IdProduktu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, productId);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // pobieranie wszystkich produkto
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Produkty";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("IdProduktu"),
                        rs.getString("NazwaProduktu"),
                        rs.getFloat("Cena"),
                        rs.getString("Opis"),
                        rs.getString("Kategoria")
                ));
            }
        }
        return products;
    }

    // pobieranie produktu po ID
    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM Produkty WHERE IdProduktu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, productId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("IdProduktu"),
                            rs.getString("NazwaProduktu"),
                            rs.getFloat("Cena"),
                            rs.getString("Opis"),
                            rs.getString("Kategoria")
                    );
                }
            }
        }
        return null;
    }

    //to pod zamowienia jest esa
    // aktualizowanie ilosci produktu
    public boolean updateProductQuantity(int idProduktu, int soldQuantity) throws SQLException {
        String query = "UPDATE Produkty SET Ilosc = Ilosc - ? WHERE IdProduktu = ? AND Ilosc >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, soldQuantity);
            pst.setInt(2, idProduktu);
            pst.setInt(3, soldQuantity);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    // przywracanie produktow w razie anulowania zamowienia
    public boolean restockProduct(int idProduktu, int addedQuantity) throws SQLException {
        String query = "UPDATE Produkty SET Ilosc = Ilosc + ? WHERE IdProduktu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, addedQuantity);
            pst.setInt(2, idProduktu);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }
}