package org.application.entity;

import java.time.LocalDate;

public class Order {
    private String idZamowienia;
    private LocalDate dataZamowienia;
    private String statusZamowienia;
    private String idKlienta;

    public Order() {
    }


    public Order(String idZamowienia, LocalDate dataZamowienia, String statusZamowienia, String idKlienta) {
        this.idZamowienia = idZamowienia;
        this.dataZamowienia = dataZamowienia;
        this.statusZamowienia = statusZamowienia;
        this.idKlienta = idKlienta;
    }

    public Order(String idZamowienia, LocalDate dataZamowienia, String statusZamowienia) {
        this.idZamowienia = idZamowienia;
        this.dataZamowienia = dataZamowienia;
        this.statusZamowienia = statusZamowienia;

    }


    public String getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(String idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public String getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(String idKlienta) {
        this.idKlienta = idKlienta;
    }

    @Override
    public String toString() {
        return "Order{\n" +
                "idZamowienia='" + idZamowienia + "\n" +
                ", dataZamowienia=" + dataZamowienia + "\n" +
                ", statusZamowienia='" + statusZamowienia + "\n" +
                ", idKlienta='" + idKlienta + "\n" +
                '}';
    }
}