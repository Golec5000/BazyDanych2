package org.application.entity;


public class Customer extends User {

    private String KlientId;

    private String address;


    public Customer(String nick, String name, String lastName, String password, String phoneNumber, String email, String address) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.address = address;
    }

    public Customer(String klientId, String nick, String name, String lastName, String password, String phoneNumber, String email, String address) {
        super(nick, name, lastName, password, phoneNumber, email);

        this.KlientId = klientId;
        this.address = address;
    }

    public String getKlientId() {
        return KlientId;
    }

    public void setKlientId(String klientId) {
        KlientId = klientId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}