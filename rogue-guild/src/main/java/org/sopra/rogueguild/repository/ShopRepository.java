package org.sopra.rogueguild.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.sopra.rogueguild.repository.model.event.WorldEvent;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemGenerator;

public class ShopRepository {
    private Map<Integer, Item> stock;
    private Random random = new Random();
    private ItemGenerator itemGenerator = new ItemGenerator();
    private WorldEvent currentEvent;
    private int id;

    public ShopRepository() {
        stock = new LinkedHashMap<>();
        loadInitialStock();
    }

    public void loadInitialStock() {
        stock.clear();
        stock.put(id = random.nextInt(100) + 1, itemGenerator.randomItemGenerator());
        stock.put(id = random.nextInt(100) + 1, itemGenerator.randomItemGenerator());
        stock.put(id = random.nextInt(100) + 1, itemGenerator.randomItemGenerator());

        this.currentEvent = new WorldEvent();
        this.currentEvent.randomWorldEvent(this);
    }

    public Item getItem(int id) {
        return stock.get(id);
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

    public void returnItem(int id, Item item) {
        stock.put(id, item);
    }

    public Map<Integer, Item> getAllStock() {
        return stock;
    }
}
