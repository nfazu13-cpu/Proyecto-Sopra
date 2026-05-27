package org.sopra.rogueguild.repository.model;

public class Boots extends Item {
    
    private int shield;

    public Boots(String name, int price, int shield) {
        super(name, price, ItemCategory.BOOTS);
        this.shield = shield;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDefensa: " + this.shield;
    }

}
