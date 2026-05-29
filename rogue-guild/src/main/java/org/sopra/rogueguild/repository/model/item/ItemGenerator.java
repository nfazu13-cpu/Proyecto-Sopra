package org.sopra.rogueguild.repository.model.item;

import java.util.ArrayList;
import java.util.List;

public class ItemGenerator {

    private final String[] WEAPON_PREFIXES = {"Espada", "Hacha", "Daga", "Lanza", "Mandoble", "Arco", "Maza", "Bastón"};
    private final String[] ARMOR_PREFIXES = {"Armadura", "Cota", "Peto", "Coraza", "Malla"};
    private final String[] POTION_PREFIXES = {"Poción", "Elixir", "Brebaje", "Ungüento", "Tintura"};
    private final String[] HELMET_PREFIXES = {"Yelmo", "Casco", "Celada", "Capucha", "Visera"};
    private final String[] BOOTS_PREFIXES = {"Botas", "Grebas", "Sandalias", "Escarpines"};

    private final String[] NATURE_SUFIXES = {
        "de fuego", "de hielo", "del rayo", "de la tormenta", "de la sombra", "de la luz",
        "de hierro", "de plata", "de obsidiana", "de acero rúnico", "de bronce antiguo",
        "del dragón", "del fénix", "del caos", "del vacío", "del alba", "de la luna",
        "del norte", "de las ruinas", "del bosque maldito", "de las profundidades", "de la montaña" 
    };

    private final List<Integer> generatedIDs = new ArrayList<>();
    private final List<String> generatedNames = new ArrayList<>();

    public Item randomItemGenerator() {
        int randomID = 0;
        String nameItem = "";
        int randomBasePrice = 0;
        int randomPrefixType;
        Item newItem = null;

        do {
            randomID = (int) (Math.random() * 594) + 1;
        } while (generatedIDs.contains(randomID));

        generatedIDs.add(randomID);

        do {

            int randomPrefix = (int) (Math.random() * 5) + 1;

            switch (randomPrefix) {
                case 1:
                    randomPrefixType = (int) (Math.random() * WEAPON_PREFIXES.length);
                    nameItem = WEAPON_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 41) + 20) * 5; // 100 - 300 gold
                    
                    int baseDamage = randomBasePrice / 10;
                    int randomDamage = baseDamage + (int)(Math.random() * 5); // 10 - 34 damage

                    newItem = new Weapon(nameItem, randomBasePrice, randomDamage);
                    break;

                case 2:
                    randomPrefixType = (int) (Math.random() * ARMOR_PREFIXES.length);
                    nameItem = ARMOR_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 31) + 10) * 5; // 50 - 200 gold

                    int baseShield = randomBasePrice / 15;
                    int randomShield = baseShield + (int)(Math.random() * 3); // 3 - 15 defense

                    newItem = new Armor(nameItem, randomBasePrice, randomShield);
                    break;

                case 3:
                    randomPrefixType = (int) (Math.random() * POTION_PREFIXES.length);
                    nameItem = POTION_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 7) + 2) * 5; // 10 - 40 gold

                    newItem = new Potion(nameItem, randomBasePrice); 
                    break;

                case 4:
                    randomPrefixType = (int) (Math.random() * HELMET_PREFIXES.length);
                    nameItem = HELMET_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 27) + 4) * 5; // 20 - 150 gold

                    int baseHelmetShield = randomBasePrice / 15;
                    int helmetShield = baseHelmetShield + (int)(Math.random() * 3); // 1 - 12 defense

                    newItem = new Helmet(nameItem, randomBasePrice, helmetShield);
                    break;

                case 5:
                    randomPrefixType = (int) (Math.random() * BOOTS_PREFIXES.length);
                    nameItem = BOOTS_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 17) + 4) * 5; // 20 - 100 gold

                    int baseBootsShield = randomBasePrice / 33;
                    int bootsShield = baseBootsShield + (int)(Math.random() * 3); // 1 - 5 defense

                    newItem = new Boots(nameItem, randomBasePrice, bootsShield);
                    break;
            }

        } while (generatedNames.contains(nameItem));

        generatedNames.add(nameItem);

        return newItem;
    }
}