package org.sopra.rogueguild.controller;

import java.util.HashMap;
import java.util.Scanner;

import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.repository.QuestRepository;
import org.sopra.rogueguild.repository.model.Quest;
import org.sopra.rogueguild.repository.model.ItemCategory;


public class QuestController {
    
    private final Player player;
    private final QuestRepository questRepository;
    private final Scanner sc = new Scanner(System.in);

    private int maxMissionID;

    public QuestController(Player p) {
        this.questRepository = new QuestRepository();
        this.player = p;
    }
    

    public void start() {
        if (questRepository.isEmpty()) {
            System.out.println("No hay misiones disponibles.");

        } else {
            maxMissionID = questRepository.getMaxID();
            questRepository.printUncompletedQuests();

            int idMission;
            do {
                System.out.println("[X]¡Seleciona el ID de una misión para aventurarte en ella!:");
                System.out.println("[0] Salir");

                idMission = askForInt();
                idMission = validateMissionID(idMission);
                
                if (idMission == -1) {
                    System.err.println("Parece que esa misión no está en la lista... \nPulsa 'ENTER' para continuar: ");
                    cleanBuffer();
                }
                
            } while (idMission == -1);

            if (idMission == 0) {
                System.out.println("Saliendo del tablón de misiones...");

            } else {
                Quest selected = missionSelector(idMission, player);
                System.out.println("Has seleccionado: " + selected.getId());

                HashMap<ItemCategory, Integer> missingRequirements = selected.checkRequierement(player);

                if (missingRequirements.isEmpty()) {
                    System.out.println("¡Cumples los requisitos para la misión!");
                    if (selected.completeMission()) {
                        System.out.println("Por ello... ¡has sido capaz de finalizar la misión " + selected.getName() + " con éxito, enhorabuena!\nAquí tienes tu pago: " + selected.getGoldReward());
                        
                        int totalReward = player.getGold() + selected.getGoldReward();
                        player.setGold(totalReward);
                    } else {
                        System.err.println("Esta misión ya ha sido completada anteriormente.");
                    }


                } else {
                    System.out.println("Para realizar esta misión, necesitas los siguientes objetos:");
                    System.out.println(missingRequirements);
                } 
            }
    
        }

        
    }


    public Quest missionSelector(int idMission, Player player) {

        Quest selectedQuest = questRepository.getQuests().get(idMission);
        return selectedQuest;

    }


    public int askForInt() {
        if (sc.hasNextInt()) {
            int number = sc.nextInt();
            cleanBuffer();
            return number;
        } else { 
            cleanBuffer();
            return -1;
        }
    }

    public int validateMissionID(int idMission) {
        if (idMission < 0 || idMission > maxMissionID) {
            return -1;
        }
        return idMission;
    }


    public void cleanBuffer() {
        sc.nextLine();
    }


}
