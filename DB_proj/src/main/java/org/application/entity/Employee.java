package org.application.entity;

import java.time.LocalDate;
import java.util.Date;

public class Employee extends User{

    private int ProcownikId;

    private String position;

    private LocalDate hireDate;

    private int adminId;

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email) {
        super(nick, name, lastName, password, phoneNumber, email);
    }

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email, String position, LocalDate hireDate, int adminId, int ProcownikId) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
        this.adminId = adminId;
        this.ProcownikId = ProcownikId;
    }

    public Employee(String nick, String name, String lastName, String phoneNumber, String email, String position, LocalDate hireDate, int adminId, int ProcownikId) {
        super(nick, name, lastName, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
        this.adminId = adminId;
        this.ProcownikId = ProcownikId;
    }

    public Employee(String nick, String name, String lastName, String password, String phoneNumber, String email, String position, LocalDate hireDate) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.position = position;
        this.hireDate = hireDate;
    }


    public int getProcownikId() {
        return ProcownikId;
    }

    public void setProcownikId(int procownikId) {
        ProcownikId = procownikId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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
