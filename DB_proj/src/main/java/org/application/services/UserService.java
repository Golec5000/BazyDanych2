package org.application.services;

import org.application.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public User addUserCustomer(String login, String name, String lastName, String address, String email, String phoneNumber, String password) {
        String query = "INSERT INTO klienci (nick, Imie, Nazwisko, Adres, Email, NumerTelefonu, Haslo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();) {

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, login);
            pst.setString(2, name);
            pst.setString(3, lastName);
            pst.setString(4, address);
            pst.setString(5, email);
            pst.setString(6, phoneNumber);
            pst.setString(7, password);

            pst.executeUpdate();

            return new User(login, name, lastName, password, phoneNumber, email, address);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User updateUserCustomer(User curr_user, String new_login, String email, String password) {
        if (new_login != null && !new_login.isBlank()) {
            // Step 1: Identify the rows in the zamowienia table that reference the nick value
            String query = "UPDATE zamowienia SET nick=? WHERE nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, new_login);
                pst.setString(2, curr_user.getNick());
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Step 2: Update the nick value in the klienci table
            query = "UPDATE klienci SET nick=? WHERE nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, new_login);
                pst.setString(2, curr_user.getNick());
                pst.executeUpdate();
                curr_user.setNick(new_login);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (email != null && !email.isBlank()) {
            String query = "UPDATE klienci SET Email=? WHERE Nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, email);
                pst.setString(2, curr_user.getNick());
                pst.executeUpdate();
                curr_user.setEmail(email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (password != null && !password.isBlank()) {
            String query = "UPDATE klienci SET Haslo=? WHERE Nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, password);
                pst.setString(2, curr_user.getNick());
                pst.executeUpdate();
                curr_user.setPassword(password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return curr_user;
    }


}
