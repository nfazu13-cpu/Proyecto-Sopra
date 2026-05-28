package org.sopra.rogueguild.repository.model;

import java.util.HashMap;

public class QuestInventory extends Quest {

    private HashMap<ItemCategory, Integer> requirements = new HashMap<>();

    public QuestInventory(String name, String description, int goldReward) {
        super(name, description, goldReward);
    }


    public void addRequierement(ItemCategory itemCategory, int quantity) {
        if (super.validateItemCategory(itemCategory) && super.validateQuantity(quantity)) {
            this.requirements.put(itemCategory, quantity);
        }

    }

    public HashMap<ItemCategory, Integer> checkRequierement(Player p) {

        HashMap<ItemCategory, Integer> missingRequirements = new HashMap<>();
        missingRequirements.putAll(this.requirements);

        for (Item item : p.getInventory().values()) {
            ItemCategory tempCategory = item.getCategory();

            if (missingRequirements.containsKey(tempCategory)) {

                int requiredQuantity = missingRequirements.get(tempCategory);
                requiredQuantity--;

                if (requiredQuantity <= 0) {
                    missingRequirements.remove(tempCategory);
                } else {
                    missingRequirements.put(tempCategory, requiredQuantity);
                }

            }
        }

        return missingRequirements;

    }


    @Override
    public String toString() {
        return super.toString() + "\nRequisitos: " + requirements;
    }

    
}
