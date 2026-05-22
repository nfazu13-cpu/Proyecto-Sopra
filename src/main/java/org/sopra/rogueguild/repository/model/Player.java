package org.sopra.rogueguild.repository.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int gold;
    private List<Item> inventory = new ArrayList<>();

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

    public List<Item> getInventory() {
        return inventory;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void buy(Item item) {
        this.gold -= item.getBasePrice();
        this.inventory.add(item);
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        if (!inventory.remove(item)) {
            System.out.println("El objeto seleccionado no se encuentra en el inventario.");
        }
    }

    public void printInventory() {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }

}
