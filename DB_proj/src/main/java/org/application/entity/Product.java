package org.application.entity;

public class Product {
    private int idProduktu;
    private String nazwaProduktu;
    private float cena;
    private String opis;
    private String kategoria;

    public Product(int idProduktu, String nazwaProduktu, float cena, String opis, String kategoria) {
        this.idProduktu = idProduktu;
        this.nazwaProduktu = nazwaProduktu;
        this.cena = cena;
        this.opis = opis;
        this.kategoria = kategoria;
    }

    public Product(int productId, String productName, float price, int quantity) {
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
