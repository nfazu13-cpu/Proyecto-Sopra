package org.sopra.rogueguild.controller;

import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.dto.BuyResponse;
import org.sopra.rogueguild.repository.model.event.WorldEvent;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.repository.model.item.Item;


public class ShopController extends UtilController {

    private final Player player;
    private final ViewDisplay view;
    private final ShopRepository repository;
    private final IncursionController incursionController;
    private final QuestController questController;
    private final EquipController equipController;

    public ShopController(Player p, ViewDisplay v, ShopRepository r, QuestController qc, EquipController ec) {
        this.player = p;
        this.view = v;
        this.repository = r;
        this.incursionController = new IncursionController();
        this.questController = qc;
        this.equipController = ec;
    }

    public void start() {

        int opt;
        do {

            view.landingPage();
            view.playerStatus(player);

            opt = super.askForInt();
            switch (opt) {
                case 1:
                    view.showWorldEventMessage(repository.getCurrentEvent());
                    view.displayStock(repository.getStock(), false);
                    break;
                case 2:
                    view.showWorldEventMessage(repository.getCurrentEvent());
                    view.displayStock(repository.getStock(), true);
                    int itemId = super.askForInt();
                    if (itemId != 0) {
                        itemId--;
                        BuyResponse buyResponse = buyProcess(itemId);
                        view.buyResult(buyResponse);
                    }
                    break;
                case 3:
                    if (!player.getInventory().isEmpty()) { // SÍ tiene ítems
                        player.printInventory();
                        System.out.print("Introduce el ID del ítem a vender: ");
                        int id = super.askForInt();
                        sellProcess(id);
                    } else {
                        System.out.println("Tu inventario está vacío. No tienes ítems para vender.");
                    }
                    break;
                case 4:
                    System.out.println("Elige qué incursion quieres hacer: ");
                    System.out.println("1. Incursion de Conquista");
                    System.out.println("2. Incursion de Saqueo");
                    System.out.println("3. Incursion Menor");
                    System.out.println("0. Salir de Incursiones");

                    int option = super.askForInt();
                    incursionController.sr = this.repository;
                    incursionController.submenu(option, player);
                    break;
                case 5:
                    questController.start();
                    break;
                case 6:
                    equipController.start();
                    break;
                case 0:
                    view.quitMessage();
                    break;
                default:
                    System.out.println("Debe introducir una opción válida.");
                    break;
            }
            view.pressKeyMessage();
            super.cleanBuffer();
        } while (opt != 0);
    }

    private BuyResponse buyProcess(int id) {

        if (id >= 0 && id < repository.getActualMaxSizeStock()) {
            Item item = repository.getItem(id);
            if (item == null) {
                return BuyResponse.notFound(id);
            }
            if (player.getGold() < item.getBasePrice()) {
                return BuyResponse.notEnoughGold(item, player.getGold());
            }
            player.buy(item);
            repository.removeItem(id);
            player.addItem(item.getId(), item);
            return BuyResponse.success(item);
        } else {
            id++;
            return BuyResponse.notFound(id);
        }   

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
        repository.returnItem(itemDelJugador);
        player.setGold(player.getGold() + precioRedondeado);

        System.out.println("Has vendido " + itemDelJugador.getName() + " por " + precioRedondeado + " monedas.");
    }

}
