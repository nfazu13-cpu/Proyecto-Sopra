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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
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
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│               ¡BOTÍN OBTENIDO!               │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("│                                              │");
        System.out.printf("│  Gold: %-37d │\n", goldReward);
        System.out.printf("│  Item: %-37s │\n", nombreItem);
        System.out.println("│                                              │");
        System.out.println("└──────────────────────────────────────────────┘");

        this.goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
        player.setGold(player.getGold() + this.goldReward);

        player.addItem(itemREward.getId(), itemREward);
    }

    public void recompensaItem(Player player) {

        Item itemMayor;

        do {
            itemMayor = itemGenerator.randomItemGenerator();
        } while (itemMayor.getBasePrice() <= 50);

        this.itemREward = itemMayor;

        String nombreItem = (this.itemREward != null) ? this.itemREward.getName() : "ninguno";
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│               ¡BOTÍN OBTENIDO!               │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("│                                              │");
        System.out.printf("│  Gold: %-37d │\n", goldReward);
        System.out.printf("│  Item: %-37s │\n", nombreItem);
        System.out.println("│                                              │");
        System.out.println("└──────────────────────────────────────────────┘");

        this.goldReward = 0;

        player.addItem(itemREward.getId(), itemREward);
    }

    public void recompensaGold(Player player) {
        this.goldReward = random.nextInt(500) + 1;
        this.itemREward = null;

        String nombreItem = (this.itemREward != null) ? this.itemREward.getName() : "ninguno";

        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│               ¡BOTÍN OBTENIDO!               │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("│                                              │");
        System.out.printf("│  Gold: %-37d │\n", goldReward);
        System.out.printf("│  Item: %-37s │\n", nombreItem);
        System.out.println("│                                              │");
        System.out.println("└──────────────────────────────────────────────┘");

        goldReward = (int) (Math.round(this.goldReward / 5.0) * 5);
        player.setGold(player.getGold() + this.goldReward);

    }

    public static void mostrarPantallaIncursion(Incursion incursion) {

        String reset = "\u001B[0m";
        String rojo = "\u001B[31m";

        String textoNombre = " Misión: " + rojo + incursion.getShortName() + reset;

        System.out.println("\n┌──────────────────────────────────────────────┐");
        System.out.println("│             ⚔️ NUEVA INCURSIÓN ⚔️            │");
        System.out.println("├──────────────────────────────────────────────┤");
        System.out.println("│                                              │");
        System.out.printf("│ %-53s │\n", textoNombre);
        System.out.println("│  Descripción:                                │");

        // Divide la descripción automáticamente si es muy larga para que no rompa la
        // caja
        String desc = incursion.getDescription();
        int anchoMax = 40;
        for (int i = 0; i < desc.length(); i += anchoMax) {
            String fragmento = desc.substring(i, Math.min(i + anchoMax, desc.length()));
            System.out.printf("│    %-41s │\n", fragmento);
        }

        System.out.println("│                                              │");
        System.out.println("└──────────────────────────────────────────────┘");
    }

}
