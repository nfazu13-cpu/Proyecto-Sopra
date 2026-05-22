package org.sopra.rogueguild.controller;

import java.lang.annotation.IncompleteAnnotationException;

import org.sopra.rogueguild.repository.model.Incursion;

import java.util.*;

public class IncursionController {
    Random random = new Random();

    public void submenu(int option) {
        System.out.println("1.Incursion de Conquista");
        System.out.println("2.Incursion de Saqueo");
        System.out.println("2.Incursion Menor");

        switch (option) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    public void incursionConquista() {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion conquista1 = new Incursion("Tienes que conquitar la aldea de rufianes al norte",
                        "Conquista Rufianes");
                conquista1.recompensaItem();
                break;

            case 2:
                Incursion conquista2 = new Incursion("Tienes que conquistar el pais vecino", "Conquista Paises");
                conquista2.recompensaItem();
                break;

            case 3:
                Incursion conquista3 = new Incursion("Conquista a la bestia durmeinte", "Conquista Bestias");
                conquista3.recompensaItem();
                break;

            case 4:
                Incursion conquista4 = new Incursion("Conquista el pueblo de dragones del sur", "Conquista Dragones");
                conquista4.recompensaItem();
                break;

        }

    }

    public void incursionSaqueo() {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion saqueo1 = new Incursion("Saquea el palacio real en busca del Mapa qe necesita el mercader",
                        "Saquea el Mapa");
                saqueo1.recompensaGold();
                break;

            case 2:
                Incursion saqueo2 = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");
                saqueo2.recompensaGold();
                break;

            case 3:
                Incursion saqueo3 = new Incursion("Saque el amuleto para el mago feliz ", "Saquea Amuleto");
                saqueo3.recompensaGold();
                break;

            case 4:
                Incursion saqueo4 = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");
                saqueo4.recompensaGold();
                break;

        }

    }

    public void incursionMenor() {
        int option = random.nextInt(4) + 1;

        switch (option) {
            case 1:
                Incursion saqueo1 = new Incursion("Saquea el palacio real en busca del Mapa qe necesita el mercader",
                        "Saquea el Mapa");
                saqueo1.recompensaGold();
                break;

            case 2:
                Incursion saqueo2 = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");
                saqueo2.recompensaGold();
                break;

            case 3:
                Incursion saqueo3 = new Incursion("Saque el amuleto para el mago feliz ", "Saquea Amuleto");
                saqueo3.recompensaGold();
                break;

            case 4:
                Incursion saqueo4 = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");
                saqueo4.recompensaGold();
                break;

        }

    }
}
