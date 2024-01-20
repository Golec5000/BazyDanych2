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
    public boolean updateOrderStatus(String NumerZamowienia, String newStatus) throws SQLException {

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL updateOrderStatus(?, ?)}")) {

            cstmt.setString(2, newStatus);
            cstmt.setInt(1, Integer.parseInt(NumerZamowienia));

            int affectedRows = cstmt.executeUpdate();

            return affectedRows > 0;
        }
    }

    // usuwanie zamówienia
    public boolean deleteOrder(int NumerZamowienia) throws SQLException {

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

    public List<Order> getOrdersByKlientId(String KlientId) throws SQLException {
        System.out.println("getorderbyid");
        System.out.println(KlientId);

        List<Order> orders = new ArrayList<>();

        try (Connection conn = databaseConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall("{CALL getOrdersById(?)}")) {

            cstmt.setString(1, KlientId);
            ResultSet rs = cstmt.executeQuery();

            while (rs.next()) {

                orders.add(new Order(
                        rs.getString("NumerZamowienia"),
                        rs.getDate(2).toLocalDate(),
                        rs.getString("StanZamowienia"),
                        KlientId
                ));

            }
        }
        return orders;
    }
}