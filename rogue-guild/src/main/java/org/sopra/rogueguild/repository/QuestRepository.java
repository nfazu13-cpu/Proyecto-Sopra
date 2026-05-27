package org.sopra.rogueguild.repository;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.ItemCategory;
import org.sopra.rogueguild.repository.model.Quest;
import org.sopra.rogueguild.repository.model.QuestInventory;
import org.sopra.rogueguild.repository.model.QuestStats;

public class QuestRepository {
    private HashMap<Integer, Quest> quests;
    
    public QuestRepository() {
        quests = new HashMap<>();
        loadMissionDanzaDeMuerte();
        loadMissionCaballeroDelFenix();
        loadMissionGuardianDeAcero();
        loadMissionMaestroDeArmas();
        loadMissionViajeroErrante();
        loadMissionInfiltracionSilenciosa();
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
        QuestInventory q1_DanzaDeMuerte = new QuestInventory("Danza de Muerte", "Debes tener equipadas 'dos armas'.", 115);

        q1_DanzaDeMuerte.addRequierement(ItemCategory.WEAPON, 2);

        quests.put(q1_DanzaDeMuerte.getId(), q1_DanzaDeMuerte);
    }

    
    private void loadMissionCaballeroDelFenix() {
        QuestInventory q2_CaballeroDelFenix = new QuestInventory("Caballero del Fénix", "Debes tener equipada 'una armadura completa'.", 185);

        q2_CaballeroDelFenix.addRequierement(ItemCategory.WEAPON, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.HELMET, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.ARMOR, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.BOOTS, 1);

        quests.put(q2_CaballeroDelFenix.getId(), q2_CaballeroDelFenix);
    }

    // Based on the player's gear | START
        private void loadMissionGuardianDeAcero() {
            QuestStats q3_GuardianDeAcero = new QuestStats("Guardián de Acero", "Equipa una armadura pesada completa (Defensa total > 22).", 150);
            

            quests.put(q3_GuardianDeAcero.getId(), q3_GuardianDeAcero);
        }

        private void loadMissionMaestroDeArmas() {
            QuestStats q4_MaestroDeArmas = new QuestStats("Maestro de Armas", "Demuestra dominio con armamento avanzado (Daño total > 55).", 220);
            

            quests.put(q4_MaestroDeArmas.getId(), q4_MaestroDeArmas);
        }

        private void loadMissionViajeroErrante() {
            QuestStats q5_ViajeroErrante = new QuestStats("Viajero Errante", "Equipa equipo ligero para esta expedición (Defensa total < 10).", 140);


            quests.put(q5_ViajeroErrante.getId(), q5_ViajeroErrante);
        }

        private void loadMissionInfiltracionSilenciosa() {
            QuestStats q6_InfiltracionSilenciosa = new QuestStats("Infiltración Silenciosa", 
            "Infíltrate en el campamento enemigo usando armamento ligero y ocultable (Daño total < 20).", 180);


            quests.put(q6_InfiltracionSilenciosa.getId(), q6_InfiltracionSilenciosa);
        }
        // Based on the player's gear | END


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
