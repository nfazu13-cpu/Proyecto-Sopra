package org.sopra.rogueguild.controller;

import java.util.Scanner;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Item;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.repository.model.WorldEvent;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.dto.BuyResponse;
import org.sopra.rogueguild.repository.QuestRepository;

public class ShopController {

    private final Player player;
    private final ViewDisplay view;
    private final ShopRepository repository;
    private final Scanner sc;
    private final WorldEvent worldEvent;
    private final IncursionController incursionController;
    private final QuestRepository questRepository;

    public ShopController(Player p, ViewDisplay v, ShopRepository r, QuestRepository q) {
        this.player = p;
        this.view = v;
        this.repository = r;
        this.sc = new Scanner(System.in);
        this.worldEvent = new WorldEvent();
        this.incursionController = new IncursionController();
        this.questRepository = new QuestRepository();
    }

    public void start() {
        worldEvent.randomWorldEvent(repository);
        view.showWorldEventMessage(worldEvent);

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
                    System.out.println("Elige que incursion quieres hacer: ");
                    System.out.println("1. Incursion de Conquista");
                    System.out.println("2. Incursion de Saqueo");
                    System.out.println("3. Incursion Menor");
                    System.out.println("0. Salir de Incursiones");

                    int option = sc.nextInt();
                    incursionController.submenu(option, player);
                    break;
                case 5:
                    questRepository.printQuests();
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
        player.addItem(id, item);
        ;
        return BuyResponse.success(item);
    }

    private void sellProcess(int id) {

        Item itemDelJugador = player.getInventory().get(id);

        if (itemDelJugador == null) {
            System.out.println("ID inválido. No tienes ese ítem en tu inventario.");
            return;
        }

        double precioVenta = itemDelJugador.getBasePrice() * 0.80;
        int precioRedondeado = (int) (Math.round(precioVenta / 5.0) * 5.0);

        player.removeItem(id);
        repository.returnItem(id, itemDelJugador);
        player.setGold(player.getGold() + precioRedondeado);

        System.out.println("Has vendido " + itemDelJugador.getName() + " por " + precioRedondeado + " monedas.");
    }

}
