package org.sopra.rogueguild.controller;

import java.util.Scanner;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.view.ViewDisplay;

public class ShopController {
    private Player player;
    private ViewDisplay view;
    private ShopRepository repository;

    public ShopController(Player p, ViewDisplay v, ShopRepository r) {
        this.player = p;
        this.view = v;
        this.repository = r;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int opt;
        String resultMessage;
        do {
            view.landingPage();
            view.playerStatus(player);
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    view.displayStock(repository.getAllStock(), true);
                    break;
                case 2:
                    view.displayStock(repository.getAllStock(), false);
                    view.showMessage("Introduce número del producto que quieres comprar ");
                    resultMessage = buyProcess(sc.nextInt());
                    view.showMessage(resultMessage);
                    break;
                case 3:
                    // TODO Logic to sell and add products to stock
                    break;
                case 4:
                    // TODO Logic to ...
                    break;
                case 0:
                    view.showMessage("Nos vemos pronto.");
                    break;
                }
                view.showMessage("Pulsa cualquier tecla para volver al menu inicial ");
                sc.nextLine();
                sc.nextLine();
        } while (opt != 0);
    }

    private String buyProcess(int id) {
        Item item = repository.getItem(id);
        if (item == null) {
            return "[!] Ese objeto no existe en nuestra tienda.";
        }
        if (player.getGold() >= item.getPrice()) {
            player.buy(item);
            repository.buyItem(id);
            return "[+] " + item.getName() + " ya está en tu equipo!";
        } else {
            return "[!] No tienes suficiente oro. Vuelve cuando hayas saqueado algo.";
        }
    }

    private void sellProcess(Item item) {
        //TODO Sell process
    }
}
