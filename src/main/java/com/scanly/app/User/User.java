package com.scanly.app.User;

import com.scanly.app.Receipt.Receipt;
import com.scanly.app.ShoppingList.ShoppingList;
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
public class User {

    private String name;
    private ShoppingList shoppingList = new ShoppingList("${name}'s shopping list");
    @Builder.Default
    private List<Receipt> receipts = new ArrayList<Receipt>();

    public User(String user) {
        this.name = user;
        this.shoppingList = new ShoppingList(user);
        this.receipts = new ArrayList<Receipt>();
    }


    public ShoppingList getShoppingList(String username){
        if(this.shoppingList == null){
            this.shoppingList = new ShoppingList(username);
            return this.shoppingList;
        } else {
            this.shoppingList.setName(username);
            return this.shoppingList;
        }
    }

    public void addReceipt(String name, Date createdOn) {
        this.receipts.add(new Receipt(name,createdOn));
    }
    public void addReceiptObject(Receipt receipt) {
        this.receipts.add(receipt);
    }
    public void addProduct(Receipt receipt, String product, String name){
    }

    public List<Receipt> setReceipts() {
        this.receipts = new ArrayList<Receipt>();
        return this.receipts;
    }
}

