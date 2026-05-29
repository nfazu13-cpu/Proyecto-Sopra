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

    public ShopController(Player p, ViewDisplay v, ShopRepository r) {
        this.player = p;
        this.view = v;
        this.repository = r;
    }

    BuyResponse buyProcess(int id) {

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


    public void sellProcess(int id) {

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
