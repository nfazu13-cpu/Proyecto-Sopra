package org.sopra.rogueguild.repository.model.item;

public class Boots extends Shield {

    public Boots(int id, String name, int price, int shield) {
        super(id, name, price, ItemCategory.BOOTS, shield);
    }

}
