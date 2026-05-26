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


    public boolean checkRequierement(Player p) {

        HashMap<ItemCategory, Integer> auxRequirements = new HashMap<>();
        auxRequirements.putAll(requirements);

        for (Item item : p.getInventory().values()) {
            ItemCategory tempCategory = item.getCategory();

            if (auxRequirements.containsKey(tempCategory)) {

                int requiredQuantity = auxRequirements.get(tempCategory);
                requiredQuantity--;

                if (requiredQuantity <= 0) {
                    auxRequirements.remove(tempCategory);
                } else {
                    auxRequirements.put(tempCategory, requiredQuantity);
                }

            }
        }

        return auxRequirements.isEmpty();

    }


    public String checkStatus() {
        if (isComplete) {
            return "¡Has completado esta misión!";
        }
        return "Misión no completada aún.";
    }


    public void completeMission() {
        if (isComplete) {
            throw new IllegalArgumentException("No se puede volver a completar esta misión.");
        }

        this.isComplete = true;
    }


    @Override
    public String toString() {
        return id + ". " + name + "\n'" + description + "'\nRecompensa: " + goldReward + " (oro)" + "\n" + checkStatus() + "\nRequisitos: " + requirements;
    }

}
