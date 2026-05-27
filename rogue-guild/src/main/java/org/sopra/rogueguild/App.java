package org.sopra.rogueguild;

import org.sopra.rogueguild.controller.ShopController;
import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.QuestController;

public class App {
    public static void main(String[] args) {
        ShopRepository repository = new ShopRepository();
        
        
        ViewDisplay view = new ViewDisplay();
        Player player = new Player("Iñigo Montolla", 500);

        QuestController questController = new QuestController(player); 
        ShopController controller = new ShopController(player, view, repository, questController);
        
        controller.start();
    }
}