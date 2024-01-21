package org.application.services;

import org.application.entity.Customer;
import org.application.entity.Employee;
import org.application.enums.Positions;

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

        try (Connection conn = databaseConnection.getConnection()) {
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
                        , Positions.valueOf(rs.getString("Stanowisko"))
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

    public String getPermissions(String login) {
        String result = "";
        String query = "SELECT Stanowisko FROM pracownicy WHERE nick=?";
        try (Connection conn = databaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("Stanowisko");
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMagazineLabel(String login) {
        String result = "";
        String query = "SELECT listamagazynow.IdMagazynu FROM listamagazynow " +
                "INNER JOIN listaadministratorow ON listaadministratorow.AdministratorId=listamagazynow.IdMagazynu " +
                "INNER JOIN pracownicy ON pracownicy.AdministratorId=listaadministratorow.AdministratorId " +
                "WHERE pracownicy.nick=?";
        try (Connection conn = databaseConnection.getConnection()) {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("IdMagazynu");
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
