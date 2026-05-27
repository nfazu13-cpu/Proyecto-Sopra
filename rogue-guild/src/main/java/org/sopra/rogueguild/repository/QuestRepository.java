package org.sopra.rogueguild.repository;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.ItemCategory;
import org.sopra.rogueguild.repository.model.Quest;

public class QuestRepository {
    private HashMap<Integer, Quest> quests;
    
    public QuestRepository() {
        quests = new HashMap<>();
        loadMissionDanzaDeMuerte();
        loadMissionCaballeroDelFenix();
        loadMissionGuardianDeAcero();
        loadMissionMaestroDeArmas();
        loadMissionViajeroErrante();
        loadMissionCaminoDelCampeon();
    }


    public int getMaxID() {
        int maxID = 0;
    
        for (Quest quest : quests.values()) {
            maxID = Math.max(maxID, quest.getId());
        }
        
        return maxID;
    }

    public HashMap<Integer, Quest> getQuests() {
        return quests;
    }

    
    public boolean isEmpty() {
        if (quests.isEmpty()) {
            return true;
        } 

        return false;
    }


    private void loadMissionDanzaDeMuerte() {
        Quest q1_DanzaDeMuerte = new Quest("Danza de Muerte", "Debes tener equipadas 'dos armas'.", 115);

        q1_DanzaDeMuerte.addRequierement(ItemCategory.WEAPON, 2);

        quests.put(q1_DanzaDeMuerte.getId(), q1_DanzaDeMuerte);
    }

    
    private void loadMissionCaballeroDelFenix() {
        Quest q2_CaballeroDelFenix = new Quest("Caballero del Fénix", "Debes tener equipada 'una armadura completa'.", 185);

        q2_CaballeroDelFenix.addRequierement(ItemCategory.WEAPON, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.HELMET, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.ARMOR, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.BOOTS, 1);

        quests.put(q2_CaballeroDelFenix.getId(), q2_CaballeroDelFenix);
    }

    //Baseds on the player's gear | IN
    private void loadMissionGuardianDeAcero() {
        Quest q3_GuardianDeAcero = new Quest("Guardián de Acero","Equipa una armadura pesada completa.", 150);

        q3_GuardianDeAcero.addRequierement(ItemCategory.ARMOR, getMaxID());

    }


    private void loadMissionMaestroDeArmas() {
        Quest q4_MaestroDeArmas = new Quest("Maestro de Armas", "Demuestra dominio con armamento avanzado.", 220);



    }


    private void loadMissionViajeroErrante() {
        Quest q5_ViajeroErrante = new Quest("Viajero Errante", "Equipa equipo ligero para largas expediciones.", 140);



    }


    private void loadMissionCaminoDelCampeon() {
        Quest q5_ViajeroErrante = new Quest("Viajero Errante","Equipa equipo ligero para largas expediciones.", 140);




    }

    //Baseds on the player's gear | OUT


    public void printUncompletedQuests() {
        for (Integer id : quests.keySet()) {

            Quest tempQuest = quests.get(id);

            if (!tempQuest.getIsComplete()) {
                System.out.println(quests.get(id) + "\n");
            }
            
        }
    }


    public void printAllQuests() {
        for (Integer id : quests.keySet()) {

            System.out.println(quests.get(id) + "\n");
            
        }
    }


}
