package org.intership.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product() {
        this.id = System.currentTimeMillis();
        this.stock = 1;
    }

    public Product(String name, String description, double price) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto: " + getName() + ", Identificador: " + id + ", Precio: " + getPrice() + ", Stock: " + stock;
    }
}
