package com.scanly.app.ShoppingList;

import com.scanly.app.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private String name;
    private List<Product> shoppingItems;

    public ShoppingList(){}

    public ShoppingList(String name){

        this.name = name;
        this.shoppingItems = new ArrayList<Product>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addShoppingItems(Product product) {
        this.shoppingItems.add(product);
    }
}


