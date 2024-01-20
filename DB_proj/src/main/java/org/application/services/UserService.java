package org.application.services;

import org.application.entity.Customer;
import org.application.entity.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    public Customer addUserCustomer(String login, String name, String lastName, String address, String email, String phoneNumber, String password) {
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

            return new Customer(login, name, lastName, password, phoneNumber, email, address);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        //TODO zrobienia funkcja
        String query = "SELECT * FROM Pracownicy";

        try (Connection conn = databaseConnection.getConnection();
        PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int PracownikId = rs.getInt("PracownikId");
                String nick = rs.getString("nick");
                String name = rs.getString("Imie");
                String lastName = rs.getString("Nazwisko");
                String phoneNumber = rs.getString("NumerTelefonu");
                String email = rs.getString("email");
                String position = rs.getString("Stanowisko");
                LocalDate hireDate = rs.getDate("DataZatrudnienia").toLocalDate();
                int adminId = rs.getInt("AdministratorId");

                Employee employee = new Employee(nick, name, lastName, phoneNumber, email, position, hireDate, adminId, PracownikId);
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }


    public Employee addUserEmployee(String nick, String name, String lastName, String position, String email, String phoneNumber, String password, LocalDate hireDate) {
        String query = "INSERT INTO pracownicy (nick, Imie, Nazwisko, ProcownikId, position, Email, NumerTelefonu, Haslo, hireDate) VALUES (?, ?, ?, ?, ?, ?, ?. ?)";

        try (Connection conn = databaseConnection.getConnection();) {

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, nick);
            pst.setString(2, name);
            pst.setString(3, lastName);
            pst.setString(4, position);
            pst.setString(5, email);
            pst.setString(6, phoneNumber);
            pst.setString(7, password);
            pst.setDate(8, Date.valueOf(hireDate));


            pst.executeUpdate();

            return new Employee(nick, name, lastName, position, email, phoneNumber);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //do wyjebania
    public boolean isNickOccupiedInCustomers(String nick) {
        String query = "SELECT * FROM klienci WHERE nick=?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, nick);

            return pst.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNickOccupiedInEmployees(String nick) {
        String query = "SELECT * FROM pracownicy WHERE nick=?";

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, nick);

            return pst.executeQuery().next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer updateUserCustomer(Customer curr_customer, String new_login, String email, String password) {
        if (new_login != null && !new_login.isBlank()) {
            String query = "UPDATE klienci SET nick=? WHERE nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, new_login);
                pst.setString(2, curr_customer.getNick());
                pst.executeUpdate();
                curr_customer.setNick(new_login);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (email != null && !email.isBlank()) {
            String query = "UPDATE klienci SET Email=? WHERE Nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, email);
                pst.setString(2, curr_customer.getNick());
                pst.executeUpdate();
                curr_customer.setEmail(email);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (password != null && !password.isBlank()) {
            String query = "UPDATE klienci SET Haslo=? WHERE Nick=?";
            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, password);
                pst.setString(2, curr_customer.getNick());
                pst.executeUpdate();
                curr_customer.setPassword(password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return curr_customer;
    }


    //mail, imie, login, numer, haslo, nazwisko
   // public void editEmployee(Employee employee) {//MAKIELAKKURWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ROB MI TA METODE musze pomyslec esa


    //}
}
