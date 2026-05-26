package org.sopra.rogueguild.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.ItemGenerator;

public class ShopRepository {
    private Map<Integer, Item> stock;
    private Random random = new Random();
    private ItemGenerator itemGenerator = new ItemGenerator();
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
    }

    public Item getItem(int id) {
        return stock.get(id);
    }

    public void removeItem(int id) {
        stock.remove(id);
    }

    public void returnItem(int id, Item item) {
        stock.put(id, item);
    }

    public Map<Integer, Item> getAllStock() {
        return stock;
    }
}