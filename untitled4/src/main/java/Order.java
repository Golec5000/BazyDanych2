import java.time.LocalDate;

public class Order {
    private int idZamowienia;
    private LocalDate dataZamowienia;
    private String statusZamowienia;
    private int idKlienta;

    public Order () {
    }


    public Order(int idZamowienia, LocalDate dataZamowienia, String statusZamowienia, int idKlienta) {
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

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }
}