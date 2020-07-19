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
    private Date lastBought;

    // overloading this constructor so that it can be build with a frequency or with default frequency.
    public ShoppingListProduct(String name, double frequency,boolean show, Date lastBought) {
       super(name);
       this.frequency = frequency;
       this.show = show;
       this.lastBought = lastBought;
    }

    public ShoppingListProduct(String name, Date lastBought) {
        super(name);
        this.frequency = 10.0;
        this.show = true;
        this.lastBought = lastBought;
    }

}
