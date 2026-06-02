package org.sopra.rogueguild.repository.model.item;

public class Helmet extends Shield {

    public Helmet(int id, String name, int price, int shield) {
        super(id, name, price, ItemCategory.HELMET, shield);
    }

}
