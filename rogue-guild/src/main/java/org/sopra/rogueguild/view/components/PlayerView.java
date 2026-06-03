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
        System.out.println("  ______________________________________________________  ");
        System.out.println(" /                                                      \\ ");
        System.out.println(" | ||                                                || | ");
        System.out.println(" | ||              INVENTARIO DEL JUGADOR            || | ");
        System.out.println(" | ||                                                || | ");
        System.out.println(" | ||________________________________________________|| | ");

        // Recorremos el mapa usando sus entradas (ID y Objeto)
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

            // ⭐ Aquí usamos el 'id' real del mapa en el [%d]
            System.out.printf(" | ||  [%d] %-26s %-11s    || |\n",
                    id, item.getName(), stats);
        }

        System.out.println(" | ||                                                || | ");
        System.out.println(" | || Volver al menú                                 || | ");
        System.out.println(" | ||________________________________________________|| | ");
    }

}
