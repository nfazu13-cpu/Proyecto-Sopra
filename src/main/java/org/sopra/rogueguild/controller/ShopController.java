package org.sopra.rogueguild.controller;

import java.util.Scanner;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.dto.BuyResponse;

public class ShopController {
    private final Player player;
    private final ViewDisplay view;
    private final ShopRepository repository;
    private final Scanner sc;

    public ShopController(Player p, ViewDisplay v, ShopRepository r) {
        this.player = p;
        this.view = v;
        this.repository = r;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        int opt;
        do {
            view.landingPage();
            view.playerStatus(player);
            opt = Integer.parseInt(sc.nextLine());
            switch (opt) {
                case 1:
                    view.displayStock(repository.getAllStock(), false);
                    break;
                case 2:
                    view.displayStock(repository.getAllStock(), true);
                    int itemId = Integer.parseInt(sc.nextLine());
                    BuyResponse buyResponse = buyProcess(itemId);
                    view.buyResult(buyResponse);
                    break;
                case 3:
                    if (!player.getInventory().isEmpty()) { // SÍ tiene ítems
                        player.printInventory();
                        System.out.print("Introduce el ID del ítem a vender: ");
                        int id = sc.nextInt();
                        sellProcess(id);
                    } else {
                        System.out.println("Tu inventario está vacío. No tienes ítems para vender.");
                    }
                    break;
                case 4:
                    // TODO Logic to ...
                    break;
                case 0:
                    view.quitMessage();
                    break;
            }
            view.pressKeyMessage();
            sc.nextLine();
        } while (opt != 0);
    }

    private BuyResponse buyProcess(int id) {
        Item item = repository.getItem(id);
        if (item == null) {
            return BuyResponse.notFound(id);
        }
        if (player.getGold() < item.getBasePrice()) {
            return BuyResponse.notEnoughGold(item, player.getGold());
        }
        player.buy(item);
        repository.removeItem(id);
        player.addItem(item);
        return BuyResponse.success(item);
    }

    private void sellProcess(int id) {

        Item itemDeTienda = repository.getAllStock().get(id);

        if (itemDeTienda == null) {
            System.out.println("Ese ID de ítem no existe en el juego.");
            return;
        }

        Item itemDelJugador = player.getInventory().stream().filter(i -> i.getName().equals(itemDeTienda.getName()))
                .findFirst().orElse(null);

        if (itemDelJugador == null) {
            System.out.println("ID inválido. No tienes ese ítem en tu inventario.");
            return;
        }
        player.removeItem(itemDelJugador);
        repository.returnItem(id, itemDelJugador);

        double precioVenta = itemDelJugador.getBasePrice() * 0.80;
        double precioRedondeado = Math.round(precioVenta / 5.0) * 5.0;

        player.setGold(player.getGold() + (int) precioRedondeado);
        System.out.println("Has vendido " + itemDelJugador.getName() + " por " + (int) precioRedondeado + " monedas.");
    }

}
