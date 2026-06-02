package org.sopra.rogueguild.repository.model.item;

import org.sopra.rogueguild.repository.model.player.Player;

public class Potion extends Item {
    private int healPoints;
    private Player player;

    public Potion(int id, String name, int price, int healPoints) {
        super(id, name, price, ItemCategory.POTION);
        this.healPoints = healPoints;
    }

    public int getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(int healPoints) {
        this.healPoints = healPoints;
    }

}
