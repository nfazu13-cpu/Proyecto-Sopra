package org.sopra.rogueguild.repository.model;

import java.util.HashMap;

public class Quest {

    private final String description;
    private final String name;
    private int goldReward;
    private HashMap<ItemCategory, Integer> requirements = new HashMap<>();
    private boolean isComplete;

    private static int maxID = 1;
    private final int id;

    public Quest (String name, String description, int goldReward) {
        setGoldReward(goldReward);
        this.id = setAutoID();
        this.name = name;
        this.description = description;
        this.isComplete = false;
    }


    public int getId() {
        return this.id;
    }

    public static int getMaxID() {
        return maxID;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public String getName() {
        return name;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }


    public int setAutoID() {
        return maxID++; 
    }

    private void setGoldReward(int goldReward) {
        if (goldReward % 5 != 0) {
            throw new IllegalArgumentException("La recompensa debe ser múltiplo de 5.");
        }

        this.goldReward = goldReward;
    }


    public void addRequierement(ItemCategory itemCategory, int quantity) {
        if (validateItemCategory(itemCategory) && validateQuantity(quantity)) {
            this.requirements.put(itemCategory, quantity);
        }

    }


    public boolean validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad no puede ser cero o inferior.");
        }

        return true;
    }


    public boolean validateItemCategory(ItemCategory itemCategory) {
        if (itemCategory == null) {
            throw new IllegalArgumentException("La categoría no puede ser null.");
        }

        return true;
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

    public String checkStatus() {
        if (isComplete) {
            return "¡Has completado esta misión!";
        }
        return "Misión no completada aún.";
    }


    public boolean completeMission() {
        if (this.isComplete) {
            return false;
        }

        this.isComplete = true;
        return true;
    }


    @Override
    public String toString() {
        return id + ". " + name + "\n'" + description + "'\nRecompensa: " + goldReward + " (oro)" + "\n" + checkStatus() + "\nRequisitos: " + requirements;
    }

}
