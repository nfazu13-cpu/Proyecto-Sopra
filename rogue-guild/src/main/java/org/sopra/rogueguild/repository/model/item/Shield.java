package org.sopra.rogueguild.repository.model.item;

public class Shield extends Item {

    private int shield;

    public Shield(String name, int price, ItemCategory category, int shield) {
        super(name, price, category);
        this.shield = shield;
        this.itemStatsType = ItemStatsType.SHIELD;
    }

    @Override
    public String toString() {
        return super.toString() + " Defensa: " + this.shield;
    }
    
    public int getShield() {
        return shield;

    }

}
