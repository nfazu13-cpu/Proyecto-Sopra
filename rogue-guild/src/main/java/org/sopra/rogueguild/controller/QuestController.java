package org.sopra.rogueguild.controller;

import org.sopra.rogueguild.repository.model.Player;

import java.util.Scanner;

import org.sopra.rogueguild.repository.QuestRepository;

public class QuestController {
    
    private Player p;
    private final QuestRepository qr;
    private Scanner sc = new Scanner(System.in);

    public QuestController() {
        this.qr = new QuestRepository();
    }
    

    public void start() {
        qr.printUncompletedQuests();
        int option = askForInt();


        submenu(option, player);
    }


    public void submenu(int option, Player player) {

        switch (option) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 0:
                System.out.println("Abandonando el tablero de misiones...");
                break;
            default:
                System.out.println("Numero invalido");
                break;
        }
    }

    public int askForInt() {
        if () {
            
        }
        return sc.nextInt();
    }


}
