package com.scanly.app.Product;

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
    private double frequency;
    private boolean show;
    private int count;
    private Date firstBought;
    private Date lastBought;

    // this is for the first time a shopping list product is added
    public ShoppingListProduct(String name, Date lastBought) {
        super(name);
        this.frequency = 10.0;
        this.show = true;
        this.count = 1;
        this.firstBought = lastBought;
        this.lastBought = lastBought;
    }


   public double updateFrequency() {
        return (this.lastBought.getTime() - this.firstBought.getTime()) / count;
   }

}
