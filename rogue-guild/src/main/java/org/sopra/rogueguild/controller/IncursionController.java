package org.sopra.rogueguild.controller;

import java.lang.annotation.IncompleteAnnotationException;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.repository.model.event.Incursion;

import java.util.*;

public class IncursionController {
    Random random = new Random();
    ShopRepository sr;

    public void submenu(int option, Player player) {

        switch (option) {
            case 1:
                incursionConquista(player);
                this.sr.loadInitialStock();
                break;
            case 2:
                incursionSaqueo(player);
                this.sr.loadInitialStock();
                break;
            case 3:
                incursionMenor(player);
                this.sr.loadInitialStock();
                break;
            case 0:
                System.out.println("Saliendo de incursiones...");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public void incursionConquista(Player player) {
        int option = random.nextInt(4) + 1;
        Incursion conquista = null;

        switch (option) {
            case 1:
                conquista = new Incursion("Tienes que conquitar la aldea de rufianes al norte",
                        "Conquista Rufianes");

                break;

            case 2:
                conquista = new Incursion("Tienes que conquistar el pais vecino", "Conquista Paises");

                break;

            case 3:
                conquista = new Incursion("Conquista a la bestia durmeinte", "Conquista Bestias");

                break;

            case 4:
                conquista = new Incursion("Conquista el pueblo de dragones del sur", "Conquista Dragones");

                break;

        }

        if (conquista != null) {
            Incursion.mostrarPantallaIncursion(conquista);
            conquista.recompensaGold(player);
        }

    }

    public void incursionSaqueo(Player player) {
        int option = random.nextInt(4) + 1;
        Incursion saqueo = null;

        switch (option) {
            case 1:
                saqueo = new Incursion("Saquea el palacio real en busca del Mapa qe necesita el mercader",
                        "Saquea el Mapa");

                break;

            case 2:
                saqueo = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");

                break;

            case 3:
                saqueo = new Incursion("Saque el amuleto para el mago feliz ", "Saquea Amuleto");

                break;

            case 4:
                saqueo = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");

                break;

        }

        if (saqueo != null) {
            Incursion.mostrarPantallaIncursion(saqueo);
            saqueo.recompensaGold(player);
        }

    }

    public void incursionMenor(Player player) {
        int option = random.nextInt(4) + 1;
        Incursion menor = null;

        switch (option) {
            case 1:
                menor = new Incursion("Saquea el palacio real en busca del Mapa que necesita el mercader",
                        "Saquea el Mapa");

                break;

            case 2:
                menor = new Incursion("Saquea el oro del ogro dormilon", "Saquea al Ogro");

                break;

            case 3:
                menor = new Incursion("Saquea el amuleto para el mago feliz ", "Saquea Amuleto");

                break;

            case 4:
                menor = new Incursion("Saqueo de los nobles malvados", "Saqueo RobinHood");

                break;

        }

        if (menor != null) {
            Incursion.mostrarPantallaIncursion(menor);
            menor.recompensaGold(player);
        }

    }
}
