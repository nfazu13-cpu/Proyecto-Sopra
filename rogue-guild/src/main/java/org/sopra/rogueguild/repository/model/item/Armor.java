package org.sopra.rogueguild.repository.model.item;

public class Armor extends Shield {

    public Armor(int id, String name, int price, int shield) {
        super(id, name, price, ItemCategory.ARMOR, shield);
    }

}
