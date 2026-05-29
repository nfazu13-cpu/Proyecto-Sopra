package org.sopra.rogueguild.repository.model.player;

import java.util.*;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemCategory;
import org.sopra.rogueguild.repository.model.item.Weapon;

public class Player {

    private String name;
    private int gold = 0;
    private Map<Integer, Item> inventory = new HashMap<>();
    Random random = new Random();
    private ArrayList<Item> Armor = new ArrayList<Item>();
    private HashMap<ItemCategory, Item[]> itemEquipped;
    private int id;
    private int currentCity;

    public Player(String name, int gold) {
        setGold(gold);
        setItemEquipped(itemEquipped);
        this.name = name;

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

    public HashMap<ItemCategory, Item[]> getItemEquipped() {
        return itemEquipped;
    }

    public void setGold(int gold) {
        if (gold < 0) {
            this.gold = 0;
        } else if (gold > 500) {
            this.gold = 500;
        } else {
            this.gold = gold;
        }
    }

    public void setItemEquipped(HashMap<ItemCategory, Item[]> itemEquipped) {
        this.itemEquipped = new HashMap<>();
        this.itemEquipped.put(ItemCategory.ARMOR, maxItems(1));
        this.itemEquipped.put(ItemCategory.BOOTS, maxItems(1));
        this.itemEquipped.put(ItemCategory.HELMET, maxItems(1));
        this.itemEquipped.put(ItemCategory.WEAPON, maxItems(2));
    }

    public void equipItem(Item nuevoItem) {
        ItemCategory categoria = nuevoItem.getCategory();
        Item[] ranuras = this.itemEquipped.get(categoria);

        if (ranuras.length == 1) {
            ranuras[0] = nuevoItem;
            Item itemAnterior = ranuras[0];

            if (itemAnterior != null) {
                id = random.nextInt();
                this.inventory.put(id, itemAnterior);
                System.out.println(itemAnterior + " ha vuelto al inventario.");
            }
        } else {

            if (ranuras[0] == null) {

                ranuras[0] = nuevoItem;
                System.out.println("Arma equipada en ranura 1.");

            } else if (ranuras[1] == null) {

                ranuras[1] = nuevoItem;
                System.out.println("Arma equipada en ranura 2.");
            } else {

                int indiceReemplazo = 0;
                Weapon arma1 = (Weapon) ranuras[1];
                Weapon arma0 = (Weapon) ranuras[0];

                if (arma1.getDamage() < arma0.getDamage()) {
                    indiceReemplazo = 1;
                }

                Item armaAnterior = ranuras[indiceReemplazo];
                ranuras[indiceReemplazo] = nuevoItem; // Reemplazo directo
                System.out.println("Sustituida arma de menor daño: " + armaAnterior);

                this.inventory.put(id, armaAnterior);
                System.out.println(armaAnterior + " ha vuelto al inventario.");

            }
        }

    }

    private Item[] maxItems(int numMax) {
        Item[] maxItems = new Item[numMax];
        return maxItems;
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
        if (inventory.isEmpty()) {
            System.out.println("Vacio");
        } else {
            boolean esPrimero = true;
            for (Item item : inventory.values()) {
                if (esPrimero) {

                    System.out.println(item);
                    esPrimero = false;
                } else {

                    System.out.println("       |                     " + item);
                }
            }
        }
    }

    public void printInventoryByCategory(ItemCategory category) {
        System.out.println("--- Objetos de la categoría: " + category + " ---");
        for (Integer id : inventory.keySet()) {
            Item item = inventory.get(id);

            if (item.getCategory() == category) {
                System.out.println("ID: [" + id + "] - " + item);
            } else {
                System.out.println("No hay de esta categoria");
            }
        }
    }

}
