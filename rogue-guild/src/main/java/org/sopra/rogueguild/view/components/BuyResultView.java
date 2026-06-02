package org.sopra.rogueguild.view.components;

import org.sopra.rogueguild.controller.dto.BuyResponse;
import org.sopra.rogueguild.repository.model.item.ItemCategory;

public class BuyResultView {
    private final MessageView messages;

    public BuyResultView(MessageView messages) {
        this.messages = messages;
    }

    public void show(BuyResponse r, ItemCategory ic) {
        switch (r.getStatus()) {

        case SUCCESS: 
                if (ic != ItemCategory.POTION) {
                    messages.showMessage("[+] " + r.getItem().getName() + " ya está en tu equipo!");
                }
                break;
        case NOT_FOUND:
                messages.showMessage("[!] Ese objeto (" + r.getRequestedId() + ") no existe en nuestra tienda.");
                break;
        case NOT_ENOUGH_GOLD:

                messages.showMessage("[!] No tienes suficiente oro. Te faltan " + r.getMissingGold() + " monedas.");
                break;
        }
    }
}
