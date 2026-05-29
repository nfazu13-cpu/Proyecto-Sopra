package org.sopra.rogueguild.view;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.sopra.rogueguild.repository.model.item.Item;
import org.sopra.rogueguild.repository.model.player.Player;
import org.sopra.rogueguild.repository.model.event.WorldEvent;
import org.sopra.rogueguild.controller.dto.BuyResponse;
import org.sopra.rogueguild.view.components.BannerView;
import org.sopra.rogueguild.view.components.BuyResultView;
import org.sopra.rogueguild.view.components.MessageView;
import org.sopra.rogueguild.view.components.PlayerView;
import org.sopra.rogueguild.view.components.StockView;

public class ViewDisplay {
    private final BannerView banner;
    private final MessageView messages;
    private final PlayerView playerView;
    private final StockView stockView;
    private final BuyResultView buyResultView;

    public ViewDisplay() {
        this(System.out, 59);
    }

    public ViewDisplay(PrintStream out, int width) {
        this.banner = new BannerView(out);
        this.messages = new MessageView(out, width);
        this.playerView = new PlayerView(out);
        this.stockView = new StockView(out);
        this.buyResultView = new BuyResultView(messages);
    }

    public void landingPage() { banner.landingPage(); }
    public void showMessage(String msg) { messages.showMessage(msg); }
    public void pressKeyMessage() { messages.pressKeyMessage(); }
    public void quitMessage() { messages.quitMessage(); }
    public void showPrompt(String prompt) { messages.showPrompt(prompt); }
    
    public void showWorldEventMessage(WorldEvent worldEvent) { 
        messages.showMessage(worldEvent.getFinalMessage()); 
    }

    public void playerStatus(Player player) { playerView.playerStatus(player); }

    public void displayStock(List<Item> stock, boolean isInPurchaseProcess) {
        stockView.displayStock(stock, isInPurchaseProcess);
    }

    public void buyResult(BuyResponse r) {
        buyResultView.show(r);
    }
}
