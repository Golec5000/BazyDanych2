package org.application.services;

import org.application.entity.Order;
import org.application.entity.Product;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderZapytania {

    private static OrderZapytania instance;

    private OrderZapytania() {
    }

    public static OrderZapytania getInstance() {
        if (instance == null) instance = new OrderZapytania();
        return instance;
    }

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    // aktualizacja statusu zamowienia
    public boolean updateOrderStatus(int NumerZamowienia, String newStatus) throws SQLException {
        //String query = "UPDATE Zamowienia SET statusZamowienia = ? WHERE NumerZamowienia = ?";
        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL updateOrderStatus(?, ?)}")) {

            cstmt.setString(1, newStatus);
            cstmt.setInt(2, NumerZamowienia);

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    // usuwanie zamówienia
    public boolean deleteOrder(int NumerZamowienia) throws SQLException {
        //String query = "DELETE FROM Zamowienia WHERE NumerZamowienia = ?";
        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL deleteOrder(?)}")){

            cstmt.setInt(1, NumerZamowienia);

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    // pobieranie wszystkich zamowien
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        //String query = "SELECT * FROM Zamowienia";
        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getAllOrders()}");
             ResultSet rs = cstmt.executeQuery()) {

            while (rs.next()) {
                orders.add(new Order(
                        rs.getString("NumerZamowienia"),
                        rs.getDate("dataTransakcji").toLocalDate(),
                        rs.getString("stanZamowienia"),
                        rs.getString("KlientId"),
                        rs.getString("Produkt")
                ));
            }
        }
        return orders;
    }

    // pobieranie zwmowien po ID
    public Order getOrderById(int NumerZamowienia) throws SQLException {
        String query = "SELECT * FROM Zamowienia WHERE NumerZamowienia = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, NumerZamowienia);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getString("NumerZamowienia"),
                        rs.getDate("dataZamowienia").toLocalDate(),
                        rs.getString("statusZamowienia"),
                        rs.getString("idKlienta")
                );
            }
        }
        return null;
    }

    public int getLastID() throws SQLException {
        //String query = "SELECT MAX(NumerZamowienia) As id FROM zamowienia";
        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getLastID()}")) {
            ResultSet resultSet = cstmt.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("id");
        }
        return 0;
    }

    //TWORZENIE NOWEGO ZAMOWIENIA
    public Order createOrder(List<Product> productList) throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDate now = LocalDate.now();
        String temp = now.toString();
        Date data = Date.valueOf(LocalDate.now());
        int currID = getLastID() + 1;
        String currState = "Nieoplacone";
        String nick = "Entity.Customer";
        StringBuilder products = new StringBuilder();
        for (int i = 0; i < productList.size(); i++) {
            products.append(productList.get(i).getNazwaProduktu());
            if (i < productList.size() - 1)
                products.append(",");
        }
        String query = "INSERT INTO zamowienia(NumerZamowienia,DataTransakcji,StanZamowienia,Nick,produkt)VALUES (?, ?,?,?,?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(currID));
            preparedStatement.setDate(2, data);
            preparedStatement.setString(3, currState);
            preparedStatement.setString(4, nick);
            preparedStatement.setString(5, products.toString());
            preparedStatement.executeUpdate();
            return new Order(String.valueOf(currID), now, currState, nick);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Order> getOrdersByKlientId(String KlientId) throws SQLException {
        System.out.println("getorderbyid");
        System.out.println(KlientId);

        List<Order> orders = new ArrayList<>();

       // String query = "SELECT Z.NumerZamowienia, Z.DataTransakcji, Z.StanZamowienia " +
       //         "FROM Klienci K " +
       //         "JOIN zamowienia Z ON K.klientId = Z.klientId " +
       //         "WHERE K.klientId = ?";

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getOrdersById(?)}")) {

            cstmt.setString(1, KlientId);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                Order order = new Order(
                        rs.getString("NumerZamowienia"),
                        rs.getDate(2).toLocalDate(),
                        rs.getString("StanZamowienia")
                );

                order.setIdKlienta(KlientId);

                orders.add(order);
            }
        }
        return orders;
    }
}