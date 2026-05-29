package org.sopra.rogueguild.repository.model.item;

public abstract class Item {
    private String name;
    private int price;
    private final int basePrice;
    private ItemCategory category;
    protected ItemStatsType itemStatsType;
    private final int id;

    public Item(int id, String name, int price, ItemCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.basePrice = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public ItemStatsType getItemStatsType() {
        return itemStatsType;
    }

    public String toString() {
        return "[" + id + "] " + name + " (" + price + " oro)";
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

}
