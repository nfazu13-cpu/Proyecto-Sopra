package org.sopra.rogueguild.repository.model;

import java.util.*;

public class Player {

    private String name;
    private int gold;
    private Map<Integer, Item> inventory = new HashMap<>();
    private ArrayList<Item> Armor = new ArrayList<Item>();

    public Player(String name, int gold) {
        this.name = name;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public Map<Integer, Item> getInventory() {
        return inventory;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void buy(Item item) {
        this.gold -= item.getBasePrice();
    }

    public void addItem(int repositoryId, Item item) {
        inventory.put(repositoryId, item);
    }

    public void removeItem(int repositoryId) {
        if (inventory.remove(repositoryId) == null) {
            System.out.println("El objeto seleccionado no se encuentra en el inventario.");
        }
    }

    public void printInventory() {
        for (Item item : inventory.values()) {
            System.out.println(item);
        }
    }

    public void printInventoryByCategory(ItemCategory category) {
        System.out.println("--- Objetos de la categoría: " + category + " ---");

        for (Integer id : inventory.keySet()) {
            Item item = inventory.get(id);

            if (item.getCategory() == category) {
                System.out.println("ID: [" + id + "] - " + item);
            }
        }
    }

}
