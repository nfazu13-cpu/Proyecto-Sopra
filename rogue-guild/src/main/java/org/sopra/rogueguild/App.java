package org.sopra.rogueguild;

import org.sopra.rogueguild.controller.ShopController;
import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.EquipController;
import org.sopra.rogueguild.controller.Menu;
import org.sopra.rogueguild.controller.QuestController;

public class App {
    public static void main(String[] args) {
        ShopRepository repository = new ShopRepository();

        ViewDisplay view = new ViewDisplay();
        Player player = new Player("Iñigo Montolla", 500);

        QuestController questController = new QuestController(player);
        EquipController equipController = new EquipController(player);
        ShopController controller = new ShopController(player, view, repository);

        Menu menu = new Menu(player, view, repository, questController, equipController, controller);

        menu.start();
    }
}