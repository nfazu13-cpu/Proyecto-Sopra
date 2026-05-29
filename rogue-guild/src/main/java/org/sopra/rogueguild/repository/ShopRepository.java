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
    private Random random = new Random();
    private ItemGenerator itemGenerator = new ItemGenerator();
    private WorldEvent currentEvent;

    public ShopRepository() {
        stock = new ArrayList<>();
        loadInitialStock();
    }

    public void loadInitialStock() {
        stock.clear();
        stock.add(itemGenerator.randomItemGenerator());
        stock.add(itemGenerator.randomItemGenerator());
        stock.add(itemGenerator.randomItemGenerator());

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

    public void printAllStock() {
        if (stock.isEmpty()) {
            System.out.println("Vacío.");
        } else {
            for (Item item : stock.values()) {
                //TODO El ID hay que cambiarlo. Se printea siempre lo mismo. Fijarse a que ID hace referencia el Case 2 de ShopController
                System.out.println("[" + id + "] " + item);
            }
        }


    }

}
