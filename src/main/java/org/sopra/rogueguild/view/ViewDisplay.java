package org.sopra.rogueguild.view;

import java.util.Map;

import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;

public class ViewDisplay {
    String R = "\u001B[0m", G = "\u001B[90m", RED = "\u001B[31m", PURPLE = "\u001B[35m";;

    public void showMessage(String message) {
        System.out.println("  ______________________________________________________");
        System.out.println(" /  __________________________________________________  \\");
        System.out.println("|| ");
        System.out.println("||  " + message);
        System.out.println("|| ");
        System.out.println(" \\______________________________________________________/");
    }

    public void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public void landingPage() {
        System.out.println("  ___________________________________________________");
        System.out.println(" /  _______________________________________________  \\");
        System.out.println("|| /                                               \\ ||");
        System.out.println("|| |  " + RED + " ___                          " + PURPLE + " _        _ " + R + "   | ||");
        System.out.println("|| |  " + RED + "| _ \\___  __ _ _  _ ___  " + PURPLE + " __ _(_)_ _ __| |" + R + "   | ||");
        System.out.println("|| |  " + RED + "|   / _ \\/ _` | || / -_) " + PURPLE + "/ _` | | | / _` |" + R + "   | ||");
        System.out.println("|| |  " + RED + "|_|_\\___/\\__, |\\_,_\\___| " + PURPLE + "\\__, |_|_|_\\__,_|" + R + "   | ||");
        System.out.println("|| |  " + RED + "         |___/           " + PURPLE + "|___/            " + R + "   | ||");
        System.out.println("|| |                                               | ||");
        System.out.println("|| | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ | ||");
        System.out.println("|| |  [1] Ver productos de la tienda               | ||");
        System.out.println("|| |  [2] Comprar un producto                      | ||");
        System.out.println("|| |" + G + "  [0] Salir                                    " + R + "| ||");
        System.out.println("|| \\_______________________________________________/ ||");
        System.out.println(" \\___________________________________________________/");
    }

    public void playerStatus(Player player) {
        System.out.println("");
        System.out.println("    +---------------------------------------------------+");
        System.out.println("    |                 " + G + "ESTADO COMPRADOR" + R + "                  |");
        System.out.println("    +--+------------------------------------------------+");
        System.out.println("       | ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░");
        System.out.println("       | ░    NOMBRE:        " + player.getName());
        System.out.println("       | ░    ORO:           " + player.getGold() + " monedas");
        System.out.println("       | ░    INVENTARIO:    Vacio" ); //TODO Implement
        System.out.println("");
    }

    public void displayStock(Map<Integer, Item> itemMap, boolean isInventory) {
        System.out.println("  ___________________________________________________");
        System.out.println(" /  _______________________________________________  \\");
        System.out.println("|| /                                               \\ ||");
        System.out.println("|| |           INVENTARIO DE LA TIENDA             | ||");
        System.out.println("|| | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ | ||");
        System.out.println("|| |                                               | ||");
        itemMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> {
                    int id = e.getKey();
                    Item item = e.getValue();
                    if (isInventory) {
                        System.out.printf("|| |  [%s] %-28s %4d oro    | ||%n",
                                "-", item.getName(), item.getPrice());
                    } else {
                        System.out.printf("|| |  [%d] %-28s %4d oro    | ||%n",
                                id, item.getName(), item.getPrice());
                    }
                });
        System.out.println("|| |                                               | ||");
        System.out.println("|| \\_______________________________________________/ ||");
        System.out.println(" \\___________________________________________________/");
    }
}
