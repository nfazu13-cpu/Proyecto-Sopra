package org.sopra.rogueguild.repository.model;

public class Incursion {
    private int goldReward;
    private Item itemREward;
    private String description, shortName;

    public Incursion(int goldReward, Item itemREward, String description, String shortName) {
        this.goldReward = (int) (Math.round(goldReward / 5.0) * 5);
        this.itemREward = itemREward;
        this.description = description;
        this.shortName = shortName;
    }

}
