package org.sopra.rogueguild.repository.model.item;

public class Potion extends Item {
    
    public Potion(int id, String name, int price) {
        super(id, name, price, ItemCategory.POTION);
    }

}
