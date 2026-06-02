package org.sopra.rogueguild.repository.model.item;

public class Potion extends Item {
    private int healPoints;

    public Potion(int id, String name, int price, int healPoints) {
        super(id, name, price, ItemCategory.POTION);
        this.healPoints = healPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        int newHealth = 0;
        newHealth = this.healPoints + healPoints;

        if (newHealth > 20) {
            this.healPoints = 20;
            return;
        }

        if (newHealth < 0) {
            this.healPoints = 0;
            return;
        }

        this.healPoints = newHealth;
        return;
    }

    @Override
    public String toString() {
        return super.toString() + "Sana: " + this.healPoints;
    }

}
