package Entity;

import java.time.LocalDate;

public class Order {
    private int idZamowienia;
    private LocalDate dataZamowienia;
    private String statusZamowienia;
    private String idKlienta;

    public Order () {
    }


    public Order(int idZamowienia, LocalDate dataZamowienia, String statusZamowienia, String idKlienta) {
        this.idZamowienia = idZamowienia;
        this.dataZamowienia = dataZamowienia;
        this.statusZamowienia = statusZamowienia;
        this.idKlienta = idKlienta;
    }

    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
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
}