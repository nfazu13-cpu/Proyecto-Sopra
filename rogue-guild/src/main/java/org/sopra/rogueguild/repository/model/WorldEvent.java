package org.sopra.rogueguild.repository.model;

import org.sopra.rogueguild.repository.ShopRepository;

public class WorldEvent {

    
    public void randomWorldEvent(ShopRepository stock) {

        boolean isGlobal = Math.random() > 0.5; //if 'isGlobal' > 0.5, event = global, else event = ItemCategory
        boolean type = Math.random() > 0.5; // if 'type' > 0.5, percentage = possitive, else percentage = negative
        //double factor = (Math.round((Math.random() * 0.40 + 0.10) * 100.0) / 100.0);
        double factor = (Math.round(((Math.random() * 0.40 + 0.10) * 5) * 100.0) / 100.0);
        double percentage;

        if (type) {
            percentage = 1 + factor;
        } else {
            percentage = 1 - factor;
        }

        ItemCategory chosenCategory = null;

        if (!isGlobal) {
            ItemCategory[] categories = ItemCategory.values();
            int randomCategory = (int) (Math.random() * categories.length);
            chosenCategory = categories[randomCategory];
        }

        modifyPrice(isGlobal, percentage, stock, chosenCategory);

    }


    public void modifyPrice(boolean isGlobal, double percentage, ShopRepository stock, ItemCategory category) {

        int eventPrice;

        if (isGlobal) {
            
            for (Item item : stock.getAllStock().values()) {

                eventPrice = (int) (item.getBasePrice() * percentage);
                item.setPrice(eventPrice); //TODO el precio es... int?
            }

        } else {

            for (Item item : stock.getAllStock().values()) {
                
                if (item.getCategory() == category) {
                    
                    eventPrice = (int) (item.getBasePrice() * percentage);
                    item.setPrice(eventPrice);
                }

            }

        }

    }





}
