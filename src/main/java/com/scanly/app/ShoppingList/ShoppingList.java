package com.scanly.app.ShoppingList;

import com.scanly.app.ShoppingListProduct.ShoppingListProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ShoppingList {

    private String name ;
    @Builder.Default
//    private List<Product> shoppingItems = new ArrayList<Product>();
    private List<ShoppingListProduct> shoppingItems = new ArrayList<ShoppingListProduct>();

    public ShoppingList(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1)+ "'s Shopping List";
//        this.shoppingItems = new ArrayList<Product>();
        this.shoppingItems = new ArrayList<ShoppingListProduct>();
    }

//    public void addShoppingItems(Product product) {
//        this.shoppingItems.add(product);
//    }
    public void addShoppingItems(ShoppingListProduct product) {
        this.shoppingItems.add(product);
    }

    public void addShoppingItemsFromRecommendations(String name) {
        Date today = new Date();
        ShoppingListProduct product = new ShoppingListProduct(name,7,true,0,today,today);
        this.shoppingItems.add(product);
    }

    public void setState(ShoppingListProduct product, Boolean bool){
        for (ShoppingListProduct listProduct: this.shoppingItems){
            if (listProduct.getName().equals(product.getName())){
                product.setShowOnList(bool);
            }
        }
    }

}


