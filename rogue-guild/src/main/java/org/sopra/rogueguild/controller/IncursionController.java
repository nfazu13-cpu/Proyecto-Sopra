package org.sopra.rogueguild.controller;

import java.lang.annotation.IncompleteAnnotationException;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Incursion;
import org.sopra.rogueguild.repository.model.Player;

import java.util.*;

public class IncursionController {
    Random random = new Random();
    ShopRepository sr = new ShopRepository();

    public void submenu(int option, Player player) {

        switch (option) {
            case 1:
                incursionConquista(player);
                break;
            case 2:
                incursionSaqueo(player);
                break;
            case 3:
                incursionMenor(player);
                break;
            case 0:
                System.out.println("Saliendo de incursiones...");
                break;
            default:
                System.out.println("Numero invalido");
                break;
        }
    }

    public void incursionConquista(Player player) {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion conquista1 = new Incursion("Tienes que conquitar la aldea de rufianes al norte",
                        "Conquista Rufianes");
                conquista1.recompensaItem(player);
                sr.loadInitialStock();

                break;

            case 2:
                Incursion conquista2 = new Incursion("Tienes que conquistar el pais vecino", "Conquista Paises");
                conquista2.recompensaItem(player);
                sr.loadInitialStock();

                break;

            case 3:
                Incursion conquista3 = new Incursion("Conquista a la bestia durmeinte", "Conquista Bestias");
                conquista3.recompensaItem(player);
                sr.loadInitialStock();

                break;

            case 4:
                Incursion conquista4 = new Incursion("Conquista el pueblo de dragones del sur", "Conquista Dragones");
                conquista4.recompensaItem(player);
                sr.loadInitialStock();

                break;

        }

    }

    public void incursionSaqueo(Player player) {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion saqueo1 = new Incursion("Saquea el palacio real en busca del Mapa qe necesita el mercader",
                        "Saquea el Mapa");
                saqueo1.recompensaGold(player);
                sr.loadInitialStock();

                break;

            case 2:
                Incursion saqueo2 = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");
                saqueo2.recompensaGold(player);
                sr.loadInitialStock();

                break;

            case 3:
                Incursion saqueo3 = new Incursion("Saque el amuleto para el mago feliz ", "Saquea Amuleto");
                saqueo3.recompensaGold(player);
                sr.loadInitialStock();

                break;

            case 4:
                Incursion saqueo4 = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");
                saqueo4.recompensaGold(player);
                sr.loadInitialStock();

                break;

        }

    }

    public void incursionMenor(Player player) {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion menor1 = new Incursion("Saquea el palacio real en busca del Mapa qe necesita el mercader",
                        "Saquea el Mapa");
                menor1.recompensaMenor(player);
                sr.loadInitialStock();

                break;

            case 2:
                Incursion menor2 = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");
                menor2.recompensaMenor(player);
                sr.loadInitialStock();

                break;

            case 3:
                Incursion menor3 = new Incursion("Saquea el amuleto para el mago feliz ", "Saquea Amuleto");
                menor3.recompensaMenor(player);
                sr.loadInitialStock();

                break;

            case 4:
                Incursion menor4 = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");
                menor4.recompensaMenor(player);
                sr.loadInitialStock();

                break;

        }

    }
}
