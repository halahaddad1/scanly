package com.scanly.app.ShoppingListProduct;

import com.scanly.app.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ShoppingListProduct extends Product {
    private double daysBetweenPurchaces;
    private boolean showOnList;
    private int timesBought;
    private Date firstBought;
    private Date lastBought;

    // this is for the first time a shopping list product is added
    public ShoppingListProduct(String name, Date lastBought) {
        super(name);
        this.daysBetweenPurchaces = 10.0;
        this.showOnList = true;
        this.timesBought = 1;
        this.firstBought = lastBought;
        this.lastBought = lastBought;
    }
public String getSuperName(){
        return super.getName();
}

   public void updateDaysBetweenPurchaces() {

       this.daysBetweenPurchaces = Math.abs(this.lastBought.getTime() - this.firstBought.getTime()) / (timesBought * (1000 * 60 * 60 * 24));
   }

}
