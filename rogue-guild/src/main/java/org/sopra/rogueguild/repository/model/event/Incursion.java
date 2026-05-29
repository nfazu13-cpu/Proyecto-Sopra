package org.sopra.rogueguild.repository.model.event;

import java.util.Random;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemGenerator;
import org.sopra.rogueguild.repository.model.player.Player;

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

    public void recompensaMenor(Player player) {
        this.goldReward = random.nextInt(30) + 1;

        Item itemMenor;

        do {
            itemMenor = itemGenerator.randomItemGenerator();
        } while (itemMenor.getBasePrice() > 50);

        this.itemREward = itemMenor;

        String nombreItem = (this.itemREward != null) ? this.itemREward.getName() : "ninguno";
        System.out.println("Has obtenido " + goldReward + " de oro y el objeto " + nombreItem + ".");
        this.goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
        player.setGold(player.getGold() + this.goldReward);

        id = random.nextInt(100) + 1;
        player.addItem(this.id, this.itemREward);
    }

    public void recompensaItem(Player player) {
        Item itemMayor;

        do {
            itemMayor = itemGenerator.randomItemGenerator();
        } while (itemMayor.getBasePrice() <= 50);

        this.itemREward = itemMayor;

        String nombreItem = (this.itemREward != null) ? this.itemREward.getName() : "ninguno";
        System.out.println("Has obtenido " + goldReward + " de oro y el objeto " + nombreItem + ".");
        this.goldReward = 0;

        id = random.nextInt(100) + 1;
        player.addItem(id, this.itemREward);

    }

    public void recompensaGold(Player player) {
        this.goldReward = random.nextInt(500) + 1;
        this.itemREward = null;

        String nombreItem = (this.itemREward != null) ? this.itemREward.getName() : "ninguno";
        System.out.println("Has obtenido " + goldReward + " de oro y el objeto " + nombreItem + ".");

        goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
        player.setGold(player.getGold() + this.goldReward);

    }

}
