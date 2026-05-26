package org.sopra.rogueguild.repository.model;

import java.util.*;

public class ItemGenerator {

    private final String[] WEAPON_PREFIXES = { "Espada", "Hacha", "Daga", "Lanza", "Mandoble", "Arco", "Maza",
            "Bastón" };
    private final String[] ARMOR_PREFIXES = { "Armadura", "Cota", "Peto", "Coraza", "Malla" };
    private final String[] POTION_PREFIXES = { "Poción", "Elixir", "Brebaje", "Ungüento", "Tintura" };
    private final String[] HELMET_PREFIXES = { "Yelmo", "Casco", "Celada", "Capucha", "Visera" };
    private final String[] BOOTS_PREFIXES = { "Botas", "Grebas", "Sandalias", "Escarpines" };
    private final String[] OTHERS_PREFIXES = { "Piedra", "Mineral", "Colgante", "Metal" };

    private final String[] NATURE_SUFIXES = {
            "de fuego", "de hielo", "del rayo", "de la tormenta", "de la sombra", "de la luz",
            "de hierro", "de plata", "de obsidiana", "de acero rúnico", "de bronce antiguo",
            "del dragón", "del fénix", "del caos", "del vacío", "del alba", "de la luna",
            "del norte", "de las ruinas", "del bosque maldito", "de las profundidades", "de la montaña"
    };

    private final List<String> generatedNames = new ArrayList<>();

    public Item randomItemGenerator() {
        Random random = new Random();
        String nameItem = "";
        int randomBasePrice = 0;
        int randomPrefixType, randomPrefix;
        Item newItem = null;

        do {
            int dado = random.nextInt(100) + 1;

            if (dado > 5) {
                randomPrefix = (int) (Math.random() * 5) + 1;
            } else {
                randomPrefix = 6;
            }

            switch (randomPrefix) {
                case 1:
                    randomPrefixType = (int) (Math.random() * WEAPON_PREFIXES.length);
                    nameItem = WEAPON_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 41) + 20) * 5; // 100 - 300

                    int randomDamage = 0;
                    // TODO por ahora el random damage no cumple con nada establecido por los
                    // tickets de Taiga
                    newItem = new Weapon(nameItem, randomBasePrice, randomDamage);
                    break;

                case 2:
                    randomPrefixType = (int) (Math.random() * ARMOR_PREFIXES.length);
                    nameItem = ARMOR_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 31) + 10) * 5; // 50 - 200

                    int randomShield = 0;
                    // TODO por ahora el random shield no cumple con nada establecido por los
                    // tickets de Taiga
                    newItem = new Armor(nameItem, randomBasePrice, randomShield);
                    break;

                case 3:
                    randomPrefixType = (int) (Math.random() * POTION_PREFIXES.length);
                    nameItem = POTION_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 7) + 2) * 5; // 10 - 40

                    newItem = new Potion(nameItem, randomBasePrice);
                    break;

                case 4:
                    randomPrefixType = (int) (Math.random() * HELMET_PREFIXES.length);
                    nameItem = HELMET_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 27) + 4) * 5; // 20 - 150

                    newItem = new Helmet(nameItem, randomBasePrice);
                    break;

                case 5:
                    randomPrefixType = (int) (Math.random() * BOOTS_PREFIXES.length);
                    nameItem = BOOTS_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 17) + 4) * 5; // 20 - 100

                    newItem = new Boots(nameItem, randomBasePrice);
                    break;
                case 6:
                    randomPrefixType = (int) (Math.random() * OTHERS_PREFIXES.length);
                    nameItem = OTHERS_PREFIXES[randomPrefixType];
                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    randomBasePrice = ((int) (Math.random() * 11) + 50) * 5; // 250 - 300

                    newItem = new Other(nameItem, randomBasePrice);
                    break;

            }

        } while (generatedNames.contains(nameItem));

        generatedNames.add(nameItem);

        return newItem;
    }
}