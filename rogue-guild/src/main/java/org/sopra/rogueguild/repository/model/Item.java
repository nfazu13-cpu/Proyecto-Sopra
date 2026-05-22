package org.sopra.rogueguild.repository.model;

public abstract class Item {
    private String name;
    private int price;
    private final int basePrice;
    private ItemCategory category;

    public Item(String name, int price, ItemCategory category) {
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

    public ItemCategory getCategory() {
        return category;
    }

    public String toString() {
        return name + " (" + price + " oro)";
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
