package org.sopra.rogueguild.repository.model;

import java.util.HashMap;

public class QuestStats extends Quest {


    public QuestStats(String name, String description, int goldReward) {
        super(name, description, goldReward);
    }


    public void addRequierement() {


    }


    public HashMap<ItemCategory, Integer> checkRequierement(Player p) {

        HashMap<ItemCategory, Integer> missingRequirements = new HashMap<>();
        missingRequirements.putAll(this.requirements);





        
        return missingRequirements;

    }


    
}
