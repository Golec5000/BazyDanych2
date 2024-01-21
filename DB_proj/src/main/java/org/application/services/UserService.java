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
        System.out.println("dupa");
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

    public Employee addUserEmployee(String nick, String name, String lastName, String password, String email, String phoneNumber, String position, LocalDate hireDate) {
        String query = "INSERT INTO pracownicy (nick, Haslo, Imie, Nazwisko, NumerTelefonu, Email, Stanowisko, DataZatrudnienia) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = databaseConnection.getConnection();) {

            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, nick);
            pst.setString(2, password);
            pst.setString(3, name);
            pst.setString(4, lastName);
            pst.setString(7, position);
            pst.setString(6, email);
            pst.setString(5, phoneNumber);
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

    public Employee updateUserEmployee(Employee curr_employee, String new_nick, String new_name, String new_lastName, String new_email, String new_phoneNumber, String new_password) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE pracownicy SET ");
        List<Object> parameters = new ArrayList<>();

        if (new_nick != null && !new_nick.isBlank()) {
            queryBuilder.append("nick = ?, ");
            parameters.add(new_nick);
            curr_employee.setNick(new_nick);
        }
        if (new_name != null && !new_name.isBlank()) {
            queryBuilder.append("Imie = ?, ");
            parameters.add(new_name);
            curr_employee.setName(new_name);
        }
        if (new_lastName != null && !new_lastName.isBlank()) {
            queryBuilder.append("Nazwisko = ?, ");
            parameters.add(new_lastName);
            curr_employee.setLastName(new_lastName);
        }
        if (new_email != null && !new_email.isBlank()) {
            queryBuilder.append("Email = ?, ");
            parameters.add(new_email);
            curr_employee.setEmail(new_email);
        }
        if (new_phoneNumber != null && !new_phoneNumber.isBlank()) {
            queryBuilder.append("NumerTelefonu = ?, ");
            parameters.add(new_phoneNumber);
            curr_employee.setPhoneNumber(new_phoneNumber);
        }
        if (new_password != null && !new_password.isBlank()) {
            queryBuilder.append("Haslo = ?, ");
            parameters.add(new_password);
            curr_employee.setPassword(new_password);
        }

        if (!parameters.isEmpty()) {
            queryBuilder.setLength(queryBuilder.length() - 2);
            queryBuilder.append(" WHERE nick = ?");
            parameters.add(curr_employee.getNick());

            String query = queryBuilder.toString();

            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                for (int i = 0; i < parameters.size(); i++) {
                    pst.setObject(i + 1, parameters.get(i));
                }

                pst.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return curr_employee;
    }
}
