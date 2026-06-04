package org.sopra.rogueguild.view.components;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.sopra.rogueguild.view.utils.Ansi.*;

import org.sopra.rogueguild.repository.model.item.Armor;
import org.sopra.rogueguild.repository.model.item.Boots;
import org.sopra.rogueguild.repository.model.item.Helmet;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.Potion;
import org.sopra.rogueguild.repository.model.item.Weapon;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.view.utils.Ansi;

public class PlayerView {
    private final PrintStream out;

    public PlayerView(PrintStream out) {
        this.out = out;
    }

    public void playerStatus(Player player) {
        out.println();
        out.println("    +---------------------------------------------------+");
        out.println("    |                 " + c(GRAY, "ESTADO COMPRADOR") + "                  |");
        out.println("    +--+------------------------------------------------+");
        out.println("       | ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        out.println("       | ░    NOMBRE:        " + player.getName());
        out.println("       | ░    ORO:           " + player.getGold() + " monedas");
        out.println("       | ░    VIDA:          " + player.getHitPoints());
        out.print("       | ░    INVENTARIO:    ");
        player.printInventoryMenu();
        out.print("       | ░    EQUIPADO:    ");
        player.printEquipmentMenu();
        out.println();
    }

    public void displayPlayerInventory(Map<Integer, Item> inventory) {
        out.println("   ______________________________________________________________");
        out.println("  /  __________________________________________________________  \\");
        out.println(" || /                                                          \\ ||");
        out.println(" || |                  INVENTARIO DEL JUGADOR                  | ||");
        out.println(" || | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ | ||");
        out.println(" || |                                                          | ||");

        for (Map.Entry<Integer, Item> entry : inventory.entrySet()) {
            Integer id = entry.getKey();
            Item item = entry.getValue();
            String stats = "";

            if (item instanceof Weapon) {
                stats = "Daño: " + ((Weapon) item).getDamage();
            } else if (item instanceof Armor) {
                stats = "Def.: " + ((Armor) item).getShield();
            } else if (item instanceof Helmet) {
                stats = "Def.: " + ((Helmet) item).getShield();
            } else if (item instanceof Boots) {
                stats = "Def.: " + ((Boots) item).getShield();
            } else if (item instanceof Potion) {
                stats = "Sana: " + ((Potion) item).getHealPoints();
            }

            System.out.printf(" || |  [%d] %-26s %-11s              | ||\n",
                    id, item.getName(), stats);
        }

        out.println(" || |                                                          | ||");
        out.println(" || |  [X] Introduce el ID del item a vender:                  | ||");
        out.println(" || |  " + Ansi.c(Ansi.GRAY,"[0] Salir") + "                                               | ||");
        out.println(" || \\__________________________________________________________/ ||");
        out.println("  \\______________________________________________________________/");
    }
}