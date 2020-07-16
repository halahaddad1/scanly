package com.scanly.app.User;

import com.scanly.app.ShoppingList.ShoppingList;
import com.scanly.app.Receipt.Receipt;

import java.util.ArrayList;
import java.util.*;

public class User {


    private Long id;
    private String name;
    private ShoppingList shoppingList;
    private List<Receipt> receipts;

    public User(){}

    public User(String name){

        this.name = name;
        this.id = id;
        this.shoppingList = new ShoppingList("shoppingList");
        this.receipts = new ArrayList<Receipt>();
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addReceipt(String name, Date createdOn) {
        this.receipts.add(new Receipt(name,createdOn));
    }

}

