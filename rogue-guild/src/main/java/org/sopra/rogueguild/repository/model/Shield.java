package org.sopra.rogueguild.repository.model;

public class Shield extends Item {

    private int shield;

    public Shield(String name, int price, ItemCategory category, int shield) {
        super(name, price, category);
        this.shield = shield;
    }

    @Override
    public String toString() {
        return super.toString() + " Defensa: " + this.shield;
    }

}
