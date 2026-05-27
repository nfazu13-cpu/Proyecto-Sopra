package org.sopra.rogueguild.repository.model;

public class Helmet extends Item {

    private int shield;

    public Helmet(String name, int price, int shield) {
        super(name, price, ItemCategory.HELMET);
        this.shield = shield;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDefensa: " + this.shield;
    }

}
