package org.sopra.rogueguild.controller;

import java.util.HashMap;
import java.util.Map;

import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;

public class EquipController {
    Player player;

    public Item equip(int id) {
        Map<Integer, Item> inventarioJugador = player.getInventory();
        Item item = inventarioJugador.get(id);
        return item;
    }

}
