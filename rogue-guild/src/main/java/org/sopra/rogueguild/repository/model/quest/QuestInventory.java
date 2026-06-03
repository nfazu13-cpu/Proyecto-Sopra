package org.sopra.rogueguild.repository.model.quest;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemCategory;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.view.utils.Ansi;

public class QuestInventory extends Quest {

    private HashMap<ItemCategory, Integer> requirements = new HashMap<>();

    public QuestInventory(String name, String description, int goldReward) {
        super(name, description, goldReward);
    }


    public void addRequierement(ItemCategory itemCategory, int quantity) {
        if (validateItemCategory(itemCategory) && super.validateQuantity(quantity)) {
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


    public boolean validateItemCategory(ItemCategory itemCategory) {
        if (itemCategory == null) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }

        return true;
    }

    @Override
    public void printRequirements() {
        System.out.println("Requisitos de " + Ansi.c(Ansi.BLUE,"INVENTARIO:"));
        for (HashMap.Entry<ItemCategory, Integer> requirements : this.requirements.entrySet()) {
            if (requirements.getKey() == ItemCategory.ARMOR) {
                System.out.println("Armadura: " + requirements.getValue());
            } else if (requirements.getKey() == ItemCategory.HELMET) {
                System.out.println("Casco: " + requirements.getValue());
            } else if (requirements.getKey() == ItemCategory.BOOTS) {
                System.out.println("Botas: " + requirements.getValue());
            } else if (requirements.getKey() == ItemCategory.WEAPON) {
                System.out.println("Armas: " + requirements.getValue());
            }
        }
    }

    
}
