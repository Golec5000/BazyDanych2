package org.application.entity;

import java.time.LocalDate;

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String orderStatus;
    private String customerId;

    private String product;

    public Order() {
    }

    public Order(String orderId, LocalDate orderDate, String orderStatus, String customerId, String product) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.product = product;
    }

    public Order(String orderId, LocalDate orderDate, String orderStatus, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
    }

    public Order(String orderId, LocalDate orderDate, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;

    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{\n" +
                "idZamowienia='" + orderId + "\n" +
                ", dataZamowienia=" + orderDate + "\n" +
                ", statusZamowienia='" + orderStatus + "\n" +
                ", idKlienta='" + customerId + "\n" +
                '}';
    }
}