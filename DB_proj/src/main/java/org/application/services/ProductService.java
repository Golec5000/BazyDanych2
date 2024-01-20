package org.application.services;


import org.application.entity.Order;
import org.application.entity.Product;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static ProductService instance;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (instance == null) instance = new ProductService();
        return instance;
    }

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public ArrayList<String> getKategorie() throws SQLException {
        ArrayList<String> listaKategori = new ArrayList<>();
        //String query = "SELECT DISTINCT Kategoria FROM produkty";
        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getKategorie()}")) {
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                listaKategori.add(rs.getString("Kategoria"));
            }
        } catch (Exception exception) {

        }
        return listaKategori;
    }

    //updatowanie ilosci pod zamowienia, z sprawdzaniem czy dana ilosc jest dostpena
    public boolean updateProductQuantity(int idProduktu, int idMagazynu, int soldQuantity) throws SQLException {
        String query = "UPDATE Magazyn SET Ilosc = Ilosc - ? WHERE IdProduktu = ? AND IdMagazynu = ? AND Ilosc >= ?";
        try (Connection conn = databaseConnection.getConnection();
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
        try (Connection conn = databaseConnection.getConnection();
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

        try (Connection con = databaseConnection.getConnection();
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

            System.out.println("Entity.Product added to warehouse successfully.");
        }
    }

    //wyswietlanie wszsystkich produktow z danego magazynu + ich ilosci
    public List<Product> displayProductsForWarehouse(int warehouseId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.IdProduktu, p.NazwaProduktu, p.Cena, m.Ilosc " +
                "FROM Produkty p " +
                "JOIN Magazyn m ON p.IdProduktu = m.IdProduktu " +
                "WHERE m.IdMagazynu = ?";

        try (Connection con = databaseConnection.getConnection();
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


        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL addProduct(?, ?, ?, ?)}")){

            cstmt.setString(1, product.getNazwaProduktu());
            cstmt.setFloat(2, product.getCena());
            cstmt.setString(3, product.getOpis());
            cstmt.setString(4, product.getKategoria());

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    //aktualizowanie istniejącego produktu
    public boolean updateProduct(Product product) throws SQLException {

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL updateProduct(?, ?, ?, ?, ?)}")) {

            cstmt.setString(1, product.getNazwaProduktu());
            cstmt.setFloat(2, product.getCena());
            cstmt.setString(3, product.getOpis());
            cstmt.setString(4, product.getKategoria());
            cstmt.setInt(5, product.getIdProduktu());

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    //usuwanie produktu
    public boolean deleteProduct(int productId) throws SQLException {

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL deleteProduct(?)}")) {

            cstmt.setInt(1, productId);

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    //pobieranie wszystkich produktow
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getAllProducts()}")) {
            ResultSet rs = cstmt.executeQuery();

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
        try (Connection conn = databaseConnection.getConnection();
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

    public int getLastID() throws SQLException {

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getLastID()}")) {
            ResultSet resultSet = cstmt.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("id");
        } catch (Exception ex) {
            return 1;
        }
        return 0;
    }

    public Order order(Product product, String KlientId) throws SQLException {

        LocalDate now = LocalDate.now();
        Date data = Date.valueOf(LocalDate.now());
        int currID = getLastID() + 1;
        String currState = "Oczekujące";

        try (Connection connection = databaseConnection.getConnection();
             CallableStatement cstmt = connection.prepareCall("{CALL orderProduct(?, ?, ?, ?, ?)}")) {

            cstmt.setString(1, String.valueOf(currID));
            cstmt.setDate(2, data);
            cstmt.setString(3, currState);
            cstmt.setString(4, KlientId);
            cstmt.setString(5, product.getNazwaProduktu());
            cstmt.executeUpdate();
            return new Order(String.valueOf(currID), now, currState, KlientId);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public List<String> getOpinionsByCustomerId(int customerId) {

        List<String> opinions = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getOpinionsByCustomerId(?)}")) {

            cstmt.setInt(1, customerId);
            ResultSet rs = cstmt.executeQuery();
            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("ID produktu: ").append(rs.getString("IdProduktu"));
                sb.append("\n");
                sb.append("Ocena: ").append(rs.getString("Ocena"));
                sb.append("\n");
                sb.append("Komentarz: ").append(rs.getString("Komentarz"));
                sb.append("\n");

                opinions.add(sb.toString());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return opinions;
    }
}