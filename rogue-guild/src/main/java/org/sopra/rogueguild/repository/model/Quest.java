package org.sopra.rogueguild.repository.model;
import java.util.ArrayList;

public class Quest {
    
    private String description;
    private int goldReward;
    private ArrayList<Item> requiredItems = new ArrayList<>();
    private boolean isComplete;

    public Quest(String description, int goldReward, ArrayList<Item> requiredItems) {
        setGoldReward(goldReward);
        this.description = description;
        this.requiredItems = requiredItems;
        this.isComplete = false;
    }


    public boolean checkRequierement(Player p) {
        int countItem = 0;

        for (Item item : requiredItems) {
            if (p.getInventory().containsValue(item)) {
                countItem++;
            }
        }

        if (countItem == requiredItems.size()) {
            return true;
        }

        return false;

    }


    public void setGoldReward(int goldReward) {
        if (goldReward % 5 != 0) {
            throw new IllegalArgumentException("La recompensa debe ser múltiplo de 5.");
        }

        this.goldReward = goldReward;
    }


    public void completeMission() {
        if (isComplete) {
            throw new IllegalArgumentException("No se puede volver a completar esta misión.");
        }

        this.isComplete = true;
    }



}
