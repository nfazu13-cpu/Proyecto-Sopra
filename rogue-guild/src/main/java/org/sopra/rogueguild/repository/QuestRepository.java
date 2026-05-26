package org.sopra.rogueguild.repository;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.ItemCategory;
import org.sopra.rogueguild.repository.model.Quest;

public class QuestRepository {
    private HashMap<String, Quest> quests;
    
    public QuestRepository() {
        quests = new HashMap<>();
    }


    private void loadMissionDanzaDeMuerte() {
        Quest q1_DanzaDeMuerte = new Quest("", 115);

        q1_DanzaDeMuerte.addRequierement(ItemCategory.WEAPON, 2);

        quests.put("Danza de Muerte", q1_DanzaDeMuerte);
    }

    
    private void loadMissionCaballeroDelFenix() {
        Quest q2_CaballeroDelFenix = new Quest("", 185);

        q2_CaballeroDelFenix.addRequierement(ItemCategory.WEAPON, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.HELMET, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.ARMOR, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.BOOTS, 1);

        quests.put("Caballero del Fénix", q2_CaballeroDelFenix);
    }


    public void printQuests() {
        for (String missionName : quests.keySet()) {
            
            System.out.println("- - - " + missionName + " - - -\n" + quests.get(missionName));
        }
    }

}
