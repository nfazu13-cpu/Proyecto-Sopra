package org.sopra.rogueguild.repository;

import java.util.HashMap;

import org.sopra.rogueguild.repository.model.item.ItemCategory;
import org.sopra.rogueguild.repository.model.item.ItemStatsType;
import org.sopra.rogueguild.repository.model.quest.Quest;
import org.sopra.rogueguild.repository.model.quest.QuestInventory;
import org.sopra.rogueguild.repository.model.quest.QuestStats;
import org.sopra.rogueguild.repository.model.quest.QuestStatsOperator;

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
        loadMissionCaballeroDeLaTorre();
        loadMissionAsesinoDeLaSombra();
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

    // Based on the player's inventory | START

    private void loadMissionDanzaDeMuerte() {
        QuestInventory q1_DanzaDeMuerte = new QuestInventory("Danza de Muerte", 
        "Debes tener almacenadas dos armas.", 115);

        q1_DanzaDeMuerte.addRequierement(ItemCategory.WEAPON, 2);

        quests.put(q1_DanzaDeMuerte.getId(), q1_DanzaDeMuerte);
    }

    
    private void loadMissionCaballeroDelFenix() {
        QuestInventory q2_CaballeroDelFenix = new QuestInventory("Caballero del Fénix", 
        "Debes tener almacenada una armadura completa.", 185);

        q2_CaballeroDelFenix.addRequierement(ItemCategory.WEAPON, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.HELMET, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.ARMOR, 1);
        q2_CaballeroDelFenix.addRequierement(ItemCategory.BOOTS, 1);

        quests.put(q2_CaballeroDelFenix.getId(), q2_CaballeroDelFenix);
    }

    // Based on the player's inventory | END



    // Based on the player's gear | START

    private void loadMissionGuardianDeAcero() {
        QuestStats q3_GuardianDeAcero = new QuestStats("Guardián de Acero", 
        "Equipa una armadura pesada completa.", 150);
        
        q3_GuardianDeAcero.addRequierement(ItemStatsType.SHIELD, 22, QuestStatsOperator.GREATER_THAN);

        quests.put(q3_GuardianDeAcero.getId(), q3_GuardianDeAcero);
    }

    private void loadMissionMaestroDeArmas() {
        QuestStats q4_MaestroDeArmas = new QuestStats("Maestro de Armas", 
        "Demuestra dominio con armamento avanzado.", 220);
        
        q4_MaestroDeArmas.addRequierement(ItemStatsType.DAMAGE, 55, QuestStatsOperator.GREATER_THAN);

        quests.put(q4_MaestroDeArmas.getId(), q4_MaestroDeArmas);
    }

    private void loadMissionViajeroErrante() {
        QuestStats q5_ViajeroErrante = new QuestStats("Viajero Errante", 
        "El viaje será largo... Equipate con armadura ligera para esta expedición.", 140);

        q5_ViajeroErrante.addRequierement(ItemStatsType.SHIELD, 10, QuestStatsOperator.LESS_THAN);

        quests.put(q5_ViajeroErrante.getId(), q5_ViajeroErrante);
    }

    private void loadMissionInfiltracionSilenciosa() {
        QuestStats q6_InfiltracionSilenciosa = new QuestStats("Infiltración Silenciosa", 
            "Infíltrate en el campamento enemigo usando armamento ligero y fácil de esconder.", 180);

        q6_InfiltracionSilenciosa.addRequierement(ItemStatsType.DAMAGE, 20, QuestStatsOperator.LESS_THAN);

        quests.put(q6_InfiltracionSilenciosa.getId(), q6_InfiltracionSilenciosa);
    }

    private void loadMissionCaballeroDeLaTorre() {
        QuestStats q7_CaballeroDeLaTorre = new QuestStats("Caballero de la Torre", 
            "Conviértete en un bastión inamovible sacrificando tu capacidad ofensiva.", 250);

        q7_CaballeroDeLaTorre.addRequierement(ItemStatsType.SHIELD, 40, QuestStatsOperator.GREATER_THAN);
        q7_CaballeroDeLaTorre.addRequierement(ItemStatsType.DAMAGE, 15, QuestStatsOperator.LESS_THAN);

        quests.put(q7_CaballeroDeLaTorre.getId(), q7_CaballeroDeLaTorre);
    }

    private void loadMissionAsesinoDeLaSombra() {
        QuestStats q8_AsesinoDeLaSombra = new QuestStats("Asesino de la Sombra", 
            "El objetivo no será fácil, pero pasar desapercibido tampoco... Ve armado hasta los dientes, pero con una armadura ultraligera.", 300);

        q8_AsesinoDeLaSombra.addRequierement(ItemStatsType.DAMAGE, 60, QuestStatsOperator.GREATER_THAN);
        q8_AsesinoDeLaSombra.addRequierement(ItemStatsType.SHIELD, 8, QuestStatsOperator.LESS_THAN);

        quests.put(q8_AsesinoDeLaSombra.getId(), q8_AsesinoDeLaSombra);
    }

    // Based on the player's gear | END


    public void printUncompletedQuests() {
        System.out.println("†====|:::::::::::::::>\n");

        for (Integer id : quests.keySet()) {

            Quest tempQuest = quests.get(id);

            if (!tempQuest.getIsComplete()) {
                Quest quest = quests.get(id);

                System.out.println(quest);
                System.out.println();
                quest.printRequirements();
                System.out.println();
                System.out.println("†====|:::::::::::::::>");
                System.out.println();
            }
            
        }
    }


    public void printAllQuests() {
        System.out.println("†====|:::::::::::::::>\n");

        for (Integer id : quests.keySet()) {

        Quest quest = quests.get(id);
        System.out.println(quest);
        System.out.println();
        quest.printRequirements();
        System.out.println();
        System.out.println("†====|:::::::::::::::>");
        System.out.println();
            
        }
    }


}
