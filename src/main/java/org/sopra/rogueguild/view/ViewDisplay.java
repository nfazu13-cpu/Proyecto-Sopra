package org.sopra.rogueguild.view;

import java.util.Map;

import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.view.model.BuyResponse;

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

    public void displayStock(Map<Integer, Item> itemMap, boolean isInPurchaseProcess) {
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
                    if (isInPurchaseProcess) {
                        System.out.printf("|| |  [%d] %-28s %4d oro    | ||%n",
                                id, item.getName(), item.getPrice());
                    } else {
                        System.out.printf("|| |  [%s] %-28s %4d oro    | ||%n",
                                "-", item.getName(), item.getPrice());
                    }
                });
        System.out.println("|| |                                               | ||");
        System.out.println("|| \\_______________________________________________/ ||");
        System.out.println(" \\___________________________________________________/");
        if (isInPurchaseProcess) {
            showMessage("Introduce número del producto que quieres comprar ");
        }
    }

    public void buyResult(BuyResponse r) {
        switch (r.getStatus()) {
        case SUCCESS -> showMessage("[+] " + r.getItem().getName() + " ya está en tu equipo!");
        case NOT_FOUND -> showMessage("[!] Ese objeto (" + r.getRequestedId() + ") no existe en nuestra tienda.");
        case NOT_ENOUGH_GOLD -> showMessage("[!] No tienes suficiente oro. Te faltan " + r.getMissingGold() + " monedas.");
        }
    }

    public void pressKeyMessage() {
        showMessage("Pulsa cualquier tecla para continuar ");
    }

    public void quitMessage() {
        showMessage("Nos vemos pronto.");
    }

}
