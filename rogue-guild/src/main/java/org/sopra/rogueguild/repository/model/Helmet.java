package org.sopra.rogueguild.repository.model;

public class Helmet extends Shield {

    public Helmet(String name, int price, int shield) {
        super(name, price, ItemCategory.HELMET, shield);
    }

}
