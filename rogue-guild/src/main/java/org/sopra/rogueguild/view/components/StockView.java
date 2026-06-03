package org.sopra.rogueguild.view.components;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.sopra.rogueguild.repository.model.item.Armor;
import org.sopra.rogueguild.repository.model.item.Boots;
import org.sopra.rogueguild.repository.model.item.Helmet;
import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.item.Potion;
import org.sopra.rogueguild.repository.model.item.Weapon;
import org.sopra.rogueguild.view.utils.Ansi;
import static org.sopra.rogueguild.view.utils.Ansi.*;

public class StockView {
    private final PrintStream out;

    public StockView(PrintStream out) {
        this.out = out;
    }

    public void displayStock(List<Item> stock, boolean inPurchase) {
        out.println("   ___________________________________________________________________");
        out.println("  /  _______________________________________________________________  \\");
        out.println(" || /                                                               \\ ||");
        out.println(" || |                    INVENTARIO DE LA TIENDA                    | ||");
        out.println(" || | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ | ||");
        out.println(" || |                                                               | ||");

        for (int i = 0; i < stock.size(); i++) {
            Item item = stock.get(i);

            String stats = "";
            if (item instanceof Weapon) {
                stats = "Daño: " + ((Weapon) item).getDamage();

            } else if (item instanceof Armor) {
                stats = "Def.: " + ((Armor) item).getShield();

            } else if (item instanceof Helmet) {
                stats = "Def.: " + ((Helmet) item).getShield();

            } else if (item instanceof Boots) {
                stats = "Def.: " + ((Boots) item).getShield();

            } else if (item instanceof Potion) {
                stats = "Sana: " + ((Potion) item).getHealPoints();

            } else {
                stats = "";
            }

            if (inPurchase) {
                out.printf(" || |  [%d] %-26s %-11s %4d oro         | ||%n", item.getId(), item.getName(), stats,
                        item.getPrice());
            } else {
                out.printf(" || |  [-] %-26s %-11s %4d oro         | ||%n", item.getName(), stats, item.getPrice());
            }
        }

        out.println(" || |" + Ansi.c(Ansi.GRAY, "  [0] Salir                                                    ")
                + "| ||");
        out.println(" || \\_______________________________________________________________/ ||");
        out.println("  \\___________________________________________________________________/");
    }
}