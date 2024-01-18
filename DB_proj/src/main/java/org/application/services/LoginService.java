package org.application.services;

import org.application.entity.User;

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

    public User userExistsCustomer(String login, String password) {

        String query = "SELECT * FROM klienci WHERE nick=? AND Haslo=?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("nick")
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

    public User userExistsEmployee(String login, String password) {

        String query = "SELECT * FROM pracownicy WHERE Imie=? AND Haslo=?";

        try(Connection conn = databaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("Nick")
                        , rs.getString("Imie")
                        , rs.getString("Nazwisko")
                        , rs.getString("Haslo")
                        , rs.getString("Telefon")
                        , rs.getString("Email")
                        , rs.getString("Adres"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
