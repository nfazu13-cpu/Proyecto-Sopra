package org.intership.model;

public class Customer {

    private String name;
    private String email;
    private Double balance;

    public Customer() {
    }

    public Customer(String name, String email, Double balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
