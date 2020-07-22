package com.scanly.app.ShoppingListProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@Builder(toBuilder = true)
public class ShoppingListProduct {
    private String name;
    private double daysBetweenPurchases;
    private boolean showOnList;
    private int timesBought;
    private Date firstBought;
    private Date lastBought;
    private static final long MILLIS_IN_A_DAY = (1000 * 60 * 60 * 24);
    private static final int PURCHASE_ADVANCE_NOTICE = 2;
    // this is for the first time a shopping list product is added
    public ShoppingListProduct(String name, Date lastBought) {
        this.name = name;
        this.daysBetweenPurchases = 7.0;
        this.showOnList = false;
        this.timesBought = 1;
        this.firstBought = lastBought;
        this.lastBought = lastBought;
    }

    public void updateDaysBetweenPurchases() {
       this.daysBetweenPurchases = Math.abs(this.lastBought.getTime() - this.firstBought.getTime()) / (timesBought * MILLIS_IN_A_DAY);
    }

    public boolean timeToBuy() {
        Date today = new Date();

        long daysFromBought = today.getTime() - this.lastBought.getTime() / MILLIS_IN_A_DAY;
        if ( daysFromBought >= this.daysBetweenPurchases - PURCHASE_ADVANCE_NOTICE ) {
            return true;
        } else {
            return false;
        }

    }

}
