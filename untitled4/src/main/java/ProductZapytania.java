import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductZapytania {

    //updatowanie ilosci pod zamowienia, z sprawdzaniem czy dana ilosc jest dostpena
    public boolean updateProductQuantity(int idProduktu, int idMagazynu, int soldQuantity) throws SQLException {
        String query = "UPDATE Magazyn SET Ilosc = Ilosc - ? WHERE IdProduktu = ? AND IdMagazynu = ? AND Ilosc >= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, soldQuantity);
            pst.setInt(2, idProduktu);
            pst.setInt(3, idMagazynu);
            pst.setInt(4, soldQuantity);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    //restockowanie produktu pod zamowienia
    public boolean restockProduct(int idProduktu, int idMagazynu, int addedQuantity) throws SQLException {
        String query = "UPDATE Magazyn SET Ilosc = Ilosc + ? WHERE IdProduktu = ? AND IdMagazynu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, addedQuantity);
            pst.setInt(2, idProduktu);
            pst.setInt(3, idMagazynu);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    //dodawanie produktu i ilosci do magazynu, jezeli juz produkt jest to updatowanie ilosci
    public void addProductToWarehouse(int warehouseId, int productId, int quantityToAdd) throws SQLException {
        String checkQuery = "SELECT Ilosc FROM Magazyn WHERE IdMagazynu = ? AND IdProduktu = ?";
        String insertQuery = "INSERT INTO Magazyn (IdMagazynu, IdProduktu, Ilosc) VALUES (?, ?, ?)";
        String updateQuery = "UPDATE Magazyn SET Ilosc = Ilosc + ? WHERE IdMagazynu = ? AND IdProduktu = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement check = con.prepareStatement(checkQuery);
             PreparedStatement insert = con.prepareStatement(insertQuery);
             PreparedStatement update = con.prepareStatement(updateQuery)) {

            con.setAutoCommit(false);

            check.setInt(1, warehouseId);
            check.setInt(2, productId);
            ResultSet resultSet = check.executeQuery();

            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("Ilosc");
                update.setInt(1, quantityToAdd);
                update.setInt(2, warehouseId);
                update.setInt(3, productId);
                update.executeUpdate();
            } else {
                insert.setInt(1, warehouseId);
                insert.setInt(2, productId);
                insert.setInt(3, quantityToAdd);
                insert.executeUpdate();
            }

            con.commit();
            con.setAutoCommit(true);

            System.out.println("Product added to warehouse successfully.");
        }
    }

    //wyswietlanie wszsystkich produktow z danego magazynu + ich ilosci
    public List<Product> displayProductsForWarehouse(int warehouseId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.IdProduktu, p.NazwaProduktu, p.Cena, m.Ilosc " +
                "FROM Produkty p " +
                "JOIN Magazyn m ON p.IdProduktu = m.IdProduktu " +
                "WHERE m.IdMagazynu = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, warehouseId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("IdProduktu");
                String productName = resultSet.getString("NazwaProduktu");
                float price = resultSet.getFloat("Cena");
                int quantity = resultSet.getInt("Ilosc");

                Product product = new Product(productId, productName, price, quantity);
                products.add(product);
            }
        }
        return products;
    }

    //dodawanie nowego produktu
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

    //aktualizowanie istniejącego produktu
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

    //usuwanie produktu
    public boolean deleteProduct(int productId) throws SQLException {
        String query = "DELETE FROM Produkty WHERE IdProduktu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, productId);

            int affectedRows = pst.executeUpdate();

            return affectedRows > 0;
        }
    }

    //pobieranie wszystkich produktow
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

    //pobieranie produktu po ID
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
}