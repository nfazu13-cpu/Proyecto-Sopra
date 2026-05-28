package org.sopra.rogueguild.repository.model;

import java.util.HashMap;

public class QuestStats extends Quest {

    private HashMap<ItemCategory, Item[]> requirements = new HashMap<>();

    public QuestStats(String name, String description, int goldReward) {
        super(name, description, goldReward);
    }


    /*public void addRequierement(, int quantity) {


    }*/


    public HashMap<ItemCategory, Item[]> checkRequierement(Player p) {

        HashMap<ItemCategory, Item[]> missingRequirements = new HashMap<>();
        missingRequirements.putAll(this.requirements);





        
        return missingRequirements;

    }


    @Override
    public String toString() {
        return super.toString() + "\nRequisitos: " + requirements;
    }
    
}
