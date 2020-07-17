package com.scanly.app.ShoppingList;

import com.scanly.app.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ShoppingList {

    private String name;
    private List<Product> shoppingItems;

    public void addShoppingItems(Product product) {
        this.shoppingItems.add(product);
    }
}


