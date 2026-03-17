package org.intership.model;

import java.util.List;

public class Purchase {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Purchase {" +
                "products=" + getProducts().toString() +
                '}';
    }
}
