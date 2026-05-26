package org.sopra.rogueguild;

import org.sopra.rogueguild.controller.EquipoController;
import org.sopra.rogueguild.controller.ShopController;
import org.sopra.rogueguild.repository.ShopRepository;
import org.sopra.rogueguild.repository.model.Player;
import org.sopra.rogueguild.view.ViewDisplay;
import org.sopra.rogueguild.controller.QuestController;

public class App {
    public static void main(String[] args) {
        ShopRepository repository = new ShopRepository();
<<<<<<< HEAD
        
        
        ViewDisplay view = new ViewDisplay();
        Player player = new Player("Iñigo Montolla", 500);

        QuestController questController = new QuestController(player); 
        ShopController controller = new ShopController(player, view, repository, questController);
        
=======
        QuestRepository questRepository = new QuestRepository();
        EquipoController equipController = new EquipoController();

        ViewDisplay view = new ViewDisplay();
        Player player = new Player("Iñigo Montolla", 500);

        ShopController controller = new ShopController(player, view, repository, questRepository, equipController);
>>>>>>> b65a1fa79d989560a2cd42da8e55002e119f432a
        controller.start();
    }
}