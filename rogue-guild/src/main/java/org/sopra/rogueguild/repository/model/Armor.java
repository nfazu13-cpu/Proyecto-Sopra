package org.sopra.rogueguild.repository.model;

public class Armor extends Shield {

    public Armor(String name, int price, int shield) {
        super(name, price, ItemCategory.ARMOR, shield);
    }

}
