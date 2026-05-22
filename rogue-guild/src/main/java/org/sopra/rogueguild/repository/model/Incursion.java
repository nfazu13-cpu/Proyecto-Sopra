package org.sopra.rogueguild.repository.model;

import java.util.Random;

public class Incursion {
    private int goldReward;
    private Item itemREward;
    private String description, shortName;
    private Player player;
    private ItemGenerator itemGenerator;
    private Random random = new Random();
    private int id;

    public Incursion(int goldReward, Item itemREward, String description, String shortName) {
        this.goldReward = (int) (Math.round(goldReward / 5.0) * 5);
        this.itemREward = itemREward;
        this.description = description;
        this.shortName = shortName;
    }

    public void iniciarIncursion() {
        int opcionRecompesa = random.nextInt(3) + 1;
        Item itemMenor;

        switch (opcionRecompesa) {
            case 1:
                recompensaMenor();
                player.setGold(player.getGold() + goldReward);
                id = random.nextInt(100) + 1;
                player.addItem(id, itemREward);
                break;
            case 2:
                recompensaItem();
                id = random.nextInt(100) + 1;
                player.addItem(id, itemREward);
                break;
            case 3:
                recompensaGold();
                player.setGold(player.getGold() + goldReward);

                break;
        }
    }

    private void recompensaMenor() {
        int goldReward = random.nextInt(100) + 1;
        Item itemMenor;

        do {
            itemMenor = itemGenerator.randomItemGenerator();
        } while (itemMenor.getBasePrice() > 50);

        itemREward = itemMenor;
    }

    private void recompensaItem() {
        Item itemMayor;

        do {
            itemMayor = itemGenerator.randomItemGenerator();
        } while (itemMayor.getBasePrice() < 50);

        itemREward = itemMayor;
    }

    private void recompensaGold() {
        int goldReward = random.nextInt(3000) + 1;
    }

}
