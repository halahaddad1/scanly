package com.scanly.app.ShoppingList;

import com.scanly.app.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder(toBuilder = true)
public class ShoppingList {

    private String name ;
    @Builder.Default
    private List<Product> shoppingItems = new ArrayList<Product>();


    public ShoppingList(String name) {
        this.name = name;
        this.shoppingItems = new ArrayList<Product>();
    }

    public void addShoppingItems(Product product) {
        this.shoppingItems.add(product);
    }
}


