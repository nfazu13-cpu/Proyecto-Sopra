package org.sopra.rogueguild.repository.model;

import java.util.ArrayList;
import java.util.HashMap;

public class QuestRepository {
    private String name;
    private HashMap<String, Quest> quests;
    
    public QuestRepository() {
        quests = new HashMap<>();
    }


    private void loadMissionDanzaDeMuerte(Player p) {
        ArrayList<Item> requiredItems = new ArrayList<>();

        Weapon w1 = new Weapon("", 0, 0);
        
        quests.addQuest("Danza de Muerte", "", 125, requiredItems);
    }
    

    public void addQuest(String name, Quest newQuest) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la misión no puede estar vacío.");
        }

        quests.put(name, newQuest);
    }

    
    public void addQuest(String name, String description, int goldReward, ArrayList<Item> requiredItems) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la misión no puede estar vacío.");
        }

        Quest newQuest = new Quest(description, goldReward, requiredItems);

        quests.put(name, newQuest);
    }




}
