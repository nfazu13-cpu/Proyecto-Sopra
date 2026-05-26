package org.sopra.rogueguild.controller;

import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.repository.model.ItemCategory;

public class EquipoController {
    private Player player;
    private ItemCategory item;

    public void submenu(int option) {
        System.out.println("1. Equip Armor");
        System.out.println("2. Equip Boots");
        System.out.println("3. Equip Helmet");
        System.out.println("4. Equip Weapon");

        switch (option) {
            case 1:
                player.printInventoryByCategory(item.ARMOR);

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 0:

                break;
        }
    }

    public void equipArmor(int id) {

    }
}
