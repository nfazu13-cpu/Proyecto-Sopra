package org.sopra.rogueguild.repository.model;

public class Potion extends Item {
    
    public Potion(String name, int price) {
        super(name, price, ItemCategory.POTION);
    }

}
