package org.sopra.rogueguild.controller;

import java.util.Map;
import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;

public class EquipController extends UtilController {
    Player player;

    public EquipController(Player player) {
        this.player = player;
    }

    public Item equip(int id) {
        Map<Integer, Item> inventarioJugador = player.getInventory();
        Item item = inventarioJugador.get(id);
        return item;
    }

    public void start() {
        if (player.getInventory().isEmpty()) {
            System.out.println("No hay ninguna pieza de equipo en el inventario.");

        } else {

            int idEquipment;
            do {
                player.printInventory();

                System.out.println("[X] Seleciona el ID de la pieza de armadura que desées equipar:");
                System.out.println("[0] Salir");

                idEquipment = super.askForInt();
                idEquipment = validateEquipmentID(idEquipment);

                    
                if (idEquipment == -1) {
                    System.err.println("Parece que ese objeto no está en el inventario... \nPulsa 'ENTER' para continuar: ");
                    super.cleanBuffer();
                }

            } while (idEquipment == -1);

            if (idEquipment == 0) {
                System.out.println("Saliendo de la selección de equipamiento...");
            } else {
                
                //TODO lógica de equipar items del inventario

            }

        }

    }


    public int validateEquipmentID(int idItem) {
        if (idItem == 0) {
            return 0;
        }

        if (player.getInventory().containsKey(idItem)) {
            return idItem;
        }

        return -1;
    }



}
