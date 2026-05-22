package org.sopra.rogueguild.repository.model;

import java.util.ArrayList;
import java.util.List;

public class ItemGenerator {

    private final String[] WEAPON_PREFIXES = { "Espada", "Hacha", "Daga", "Lanza", "Mandoble", "Arco", "Maza",
            "Bastón" };
    private final String[] ARMOR_PREFIXES = { "Armadura", "Cota", "Peto", "Coraza", "Malla" };
    private final String[] POTION_PREFIXES = { "Poción", "Elixir", "Brebaje", "Ungüento", "Tintura" };
    private final String[] HELMET_PREFIXES = { "Yelmo", "Casco", "Celada", "Capucha", "Visera" };
    private final String[] BOOTS_PREFIXES = { "Botas", "Grebas", "Sandalias", "Escarpines" };

    private final String[] NATURE_SUFIXES = {
            "de fuego", "de hielo", "del rayo", "de la tormenta", "de la sombra", "de la luz",
            "de hierro", "de plata", "de obsidiana", "de acero rúnico", "de bronce antiguo",
            "del dragón", "del fénix", "del caos", "del vacío", "del alba", "de la luna",
            "del norte", "de las ruinas", "del bosque maldito", "de las profundidades", "de la montaña"
    };

    private final List<String> generatedNames = new ArrayList<>();

    public Item randomItemGenerator() {
        String nameItem = "";
        int randomBasePrice = 0;
        int randomPrefixType;
        Item newItem = null;

        do {

            int randomPrefix = (int) (Math.random() * 5) + 1;

            switch (randomPrefix) {
                case 1:
                    randomPrefixType = (int) (Math.random() * WEAPON_PREFIXES.length);
                    nameItem = WEAPON_PREFIXES[randomPrefixType];
                    randomBasePrice = ((int) (Math.random() * 41) + 20) * 5; // 100 - 300

                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    int randomDamage = 0;
                    // TODO por ahora el random damage no cumple con nada establecido por los
                    // tickets de Taiga
                    newItem = new Weapon(nameItem, randomBasePrice, randomDamage);
                    break;

                case 2:
                    randomPrefixType = (int) (Math.random() * ARMOR_PREFIXES.length);
                    nameItem = ARMOR_PREFIXES[randomPrefixType];
                    randomBasePrice = ((int) (Math.random() * 31) + 10) * 5; // 50 - 200

                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];

                    int randomShield = 0;
                    // TODO por ahora el random shield no cumple con nada establecido por los
                    // tickets de Taiga
                    newItem = new Armor(nameItem, randomBasePrice, randomShield);
                    break;

                case 3:
                    randomPrefixType = (int) (Math.random() * POTION_PREFIXES.length);
                    nameItem = POTION_PREFIXES[randomPrefixType];
                    randomBasePrice = ((int) (Math.random() * 7) + 2) * 5; // 10 - 40

                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];
                    newItem = new Potion(nameItem, randomBasePrice);
                    break;

                case 4:
                    randomPrefixType = (int) (Math.random() * HELMET_PREFIXES.length);
                    nameItem = HELMET_PREFIXES[randomPrefixType];
                    randomBasePrice = ((int) (Math.random() * 27) + 4) * 5; // 20 - 150

                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];
                    newItem = new Helmet(nameItem, randomBasePrice);
                    break;

                case 5:
                    randomPrefixType = (int) (Math.random() * BOOTS_PREFIXES.length);
                    nameItem = BOOTS_PREFIXES[randomPrefixType];
                    randomBasePrice = ((int) (Math.random() * 17) + 4) * 5; // 20 - 150

                    nameItem += " " + NATURE_SUFIXES[(int) (Math.random() * NATURE_SUFIXES.length)];
                    newItem = new Boots(nameItem, randomBasePrice);
                    break;
            }

        } while (generatedNames.contains(nameItem));

        generatedNames.add(nameItem);

        return newItem;
    }
}
