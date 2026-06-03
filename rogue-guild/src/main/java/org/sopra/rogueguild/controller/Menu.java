package org.sopra.rogueguild.controller;

import org.sopra.rogueguild.controller.dto.BuyResponse;
import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.ShopController;
import org.sopra.rogueguild.repository.model.item.ItemCategory;

public class Menu extends UtilController {
    private final Player player;
    private final ViewDisplay view;
    private final ShopRepository repository;
    private final IncursionController incursionController;
    private final QuestController questController;
    private final EquipController equipController;
    private final ShopController shopController;

    public Menu(Player p, ViewDisplay v, ShopRepository r, QuestController qc, EquipController ec, ShopController sp) {
        this.player = p;
        this.view = v;
        this.repository = r;
        this.incursionController = new IncursionController();
        this.questController = qc;
        this.equipController = ec;
        this.shopController = sp;
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
                        Item item = repository.getItemById(itemId);

                        if (item != null) {
                            BuyResponse buyResponse = shopController.buyProcess(itemId);
                            view.buyResult(buyResponse, item.getCategory());
                        } else {
                            System.out.println("Ese item no existe.");
                        }
                    }
                    break;
                case 3:
                    if (!player.getInventory().isEmpty()) {
                        player.printInventory();
                        System.out.print("Introduce el ID del ítem a vender: ");
                        int id = super.askForInt();
                        shopController.sellProcess(id);
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

}
