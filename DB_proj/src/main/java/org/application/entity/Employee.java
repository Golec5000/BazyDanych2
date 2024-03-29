package org.application.entity;

import org.application.enums.Positions;

import java.time.LocalDate;

public class Employee extends User{

    private int employeeId;

    private Positions position;

    private LocalDate hireDate;

    private int adminId;

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email) {
        super(nick, name, lastName, password, phoneNumber, email);
    }

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email, Positions position, LocalDate hireDate, int adminId, int EmployeeId) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
        this.adminId = adminId;
        this.employeeId = EmployeeId;
    }

    public Employee(String nick, String name, String lastName, String phoneNumber, String email, Positions position, LocalDate hireDate, int adminId, int EmployeeId) {
        super(nick, name, lastName, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
        this.adminId = adminId;
        this.employeeId = EmployeeId;
    }

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email, Positions position, LocalDate hireDate) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
