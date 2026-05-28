package org.sopra.rogueguild.repository.model;

public class Weapon extends Item {

    private int damage;

    public Weapon(String name, int price, int damage) {
        super(name, price, ItemCategory.WEAPON);
        this.damage = damage;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDaño: " + this.damage;
    }

    public int getDamage() {
        return damage;
    }

}
