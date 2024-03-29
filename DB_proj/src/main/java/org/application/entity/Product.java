package org.application.entity;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String description;
    private String kategoria;

    public Product(int productId, String productName, float price, String description, String kategoria) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.kategoria = kategoria;
    }

    public Product(int productId, String productName, float price, int quantity) {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
}
