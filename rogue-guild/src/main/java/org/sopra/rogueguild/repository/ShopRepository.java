package org.sopra.rogueguild.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.sopra.rogueguild.repository.model.event.WorldEvent;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemGenerator;
import org.sopra.rogueguild.repository.model.item.ItemStatsType;

public class ShopRepository {
    private List<Item> stock;
    private ItemGenerator itemGenerator = new ItemGenerator();
    private WorldEvent currentEvent;
    private int initialStockSize = 3;

    public ShopRepository() {
        stock = new ArrayList<>();
        loadInitialStock();
    }

    public void loadInitialStock() {
        stock.clear();

        if (!itemGenerator.getMaxCombinationMade()) {
            for (int i = 0; i < initialStockSize; i++) {
                Item newItem = itemGenerator.randomItemGenerator();
                if (newItem == null) {
                    break; 
                }
                stock.add(newItem);
            }

            if (!stock.isEmpty()) {
                this.currentEvent = new WorldEvent();
                this.currentEvent.randomWorldEvent(this);
            }
        } else {
            System.err.println("Se ha alcanzado el límite de combinaciones de objetos generados por partida.");
        }
    }

    public Item getItem(int id) {
        return stock.get(id);
    }

    public Item getItemById(int itemId) {
        for (Item item : stock) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    public void removeItemById(int itemId) {

        Item itemToRemove = null;

        for (Item item : stock) {
            if (item.getId() == itemId) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            stock.remove(itemToRemove);

            if (stock.isEmpty()) {
                System.out.println(
                    "\n[!] La tienda se ha quedado sin existencias. El mercader repone el inventario..."
                );
                loadInitialStock();
            }
        }
    }

    public List<Item> getStock() {
        return stock;
    }

    public WorldEvent getCurrentEvent() {
        return currentEvent;
    }

    public void removeItem(int id) {
        stock.remove(id);

        if (stock.isEmpty()) {
            System.out.println("\n[!] La tienda se ha quedado sin existencias. El mercader repone el inventario...");
            loadInitialStock();
        }
    }

    public void returnItem(Item item) {
        stock.add(item);
    }

    public void printAllStock() {
        if (stock.isEmpty()) {
            System.out.println("Vacío.");
        } else {
            for (Item item : stock) {
                System.out.println(item);
            }
        }


    }

}
