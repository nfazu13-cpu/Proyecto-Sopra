package org.sopra.rogueguild.controller;

import java.util.Map;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.ItemCategory;
import org.sopra.rogueguild.repository.model.player.Player;

public class EquipController extends UtilController {
    Player player;

    public EquipController(Player player) {
        this.player = player;
    }

    public void start() {
        if (player.getInventory().isEmpty()) {
            System.out.println("No hay ninguna pieza de equipo en el inventario.");

        } else {

            int idEquipment;

            System.out.println("Que quieres equipar: ");
            System.out.println("1.Equipar Armadura");
            System.out.println("2.Equipar Botas");
            System.out.println("3.Equipar Casco");
            System.out.println("4.Equipar Arma");
            System.out.println("0.Salir");

            idEquipment = super.askForInt();

            switch (idEquipment) {
                case 1:
                    player.printInventoryByCategory(ItemCategory.ARMOR);
                    idEquipment = super.askForInt();
                    if (validateEquipmentID(idEquipment)) {
                        equip(idEquipment);
                    }
                    break;
                case 2:
                    player.printInventoryByCategory(ItemCategory.BOOTS);
                    idEquipment = super.askForInt();
                    if (validateEquipmentID(idEquipment)) {
                        equip(idEquipment);
                    }
                    break;

                case 3:
                    player.printInventoryByCategory(ItemCategory.HELMET);
                    idEquipment = super.askForInt();
                    if (validateEquipmentID(idEquipment)) {
                        equip(idEquipment);
                    }
                    break;

                case 4:
                    player.printInventoryByCategory(ItemCategory.WEAPON);
                    idEquipment = super.askForInt();
                    if (validateEquipmentID(idEquipment)) {
                        equip(idEquipment);
                    }
                    break;
                case 0:
                    System.out.println("Saliendo de equipamiento...");
                    break;
                default:
                    System.out.println("No es valido");
                    break;
            }

        }

    }

    public void equip(int id) {
        Map<Integer, Item> inventarioJugador = player.getInventory();
        Item item = inventarioJugador.get(id);

        if (item != null) {
            ItemCategory itemCategory = item.getCategory();

            player.equipItem(item);
        }

    }

    public boolean validateEquipmentID(int idItem) {
        if (idItem <= 0) {
            System.out.println("ID inválido");
            return false;
        }

        if (!player.getInventory().containsKey(idItem)) {
            System.out.println("No tienes ese ítem en el inventario");
            return false;
        }

        return true;
    }

}