package org.sopra.rogueguild.repository.model;

import java.util.Random;

public class Incursion {
    private int goldReward;
    private Item itemREward;
    private String description, shortName;
    private ItemGenerator itemGenerator = new ItemGenerator();
    private Random random = new Random();
    private int id;

    public Incursion(String description, String shortName) {
        if (description == null || description.isBlank() || shortName == null || shortName.isBlank()) {
            throw new IllegalArgumentException("La descripción y el nombre corto no pueden estar vacíos.");
        }

        this.description = description;
        this.shortName = shortName;
    }

    public void iniciarIncursion(Player player) {
        int opcionRecompesa = random.nextInt(3) + 1;
        Item itemMenor;

        switch (opcionRecompesa) {
            case 1:
                recompensaMenor();
                this.goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
                player.setGold(player.getGold() + this.goldReward);
                id = random.nextInt(100) + 1;
                player.addItem(this.id, this.itemREward);
                break;
            case 2:
                recompensaItem();
                this.goldReward = 0;
                id = random.nextInt(100) + 1;
                player.addItem(id, this.itemREward);
                break;
            case 3:
                this.itemREward = null;
                recompensaGold();
                goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
                player.setGold(player.getGold() + this.goldReward);

                break;
        }
    }

    private void recompensaMenor() {
        this.goldReward = random.nextInt(100) + 1;
        Item itemMenor;

        do {
            itemMenor = itemGenerator.randomItemGenerator();
        } while (itemMenor.getBasePrice() > 50);

        this.itemREward = itemMenor;
    }

    private void recompensaItem() {
        Item itemMayor;

        do {
            itemMayor = itemGenerator.randomItemGenerator();
        } while (itemMayor.getBasePrice() < 50);

        this.itemREward = itemMayor;
    }

    private void recompensaGold() {
        this.goldReward = random.nextInt(500) + 1;
    }

}
