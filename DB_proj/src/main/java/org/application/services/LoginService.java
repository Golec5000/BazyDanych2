package org.application.services;

import org.application.entity.Customer;
import org.application.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    private static LoginService instance;

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) instance = new LoginService();
        return instance;
    }

    public Customer userExistsCustomer(String login, String password) {

        String query = "SELECT * FROM klienci WHERE nick=? AND Haslo=?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Customer(rs.getString("KlientId")
                        , rs.getString("nick")
                        , rs.getString("Imie")
                        , rs.getString("Nazwisko")
                        , rs.getString("Adres")
                        , rs.getString("Email")
                        , rs.getString("NumerTelefonu")
                        , rs.getString("Haslo"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Employee userExistsEmployee(String login, String password) {

        String query = "SELECT * FROM pracownicy WHERE nick=? AND Haslo=?";

        try(Connection conn = databaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new Employee(rs.getString("nick")
                        , rs.getString("Imie")
                        , rs.getString("Nazwisko")
                        , rs.getString("Email")
                        , rs.getString("NumerTelefonu")
                        , rs.getString("Haslo")
                        , rs.getString("Stanowisko")
                        , rs.getDate("DataZatrudnienia").toLocalDate()
                        , rs.getInt("AdministratorId")
                        , rs.getInt("PracownikId")
                );

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
