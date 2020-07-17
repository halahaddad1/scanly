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
    private ShoppingList shoppingList = new ShoppingList(name+"'s shopping list");
    @Builder.Default
    private List<Receipt> receipts = new ArrayList<Receipt>();


    public ShoppingList getShoppingList(){
        if(this.shoppingList == null){
            this.shoppingList = new ShoppingList(this.name+"'s shopping list");
            return this.shoppingList;
        } else {
            return this.shoppingList;
        }
    }

    public void addReceipt(String name, Date createdOn) {
        this.receipts.add(new Receipt(name,createdOn));
    }
    public void addReceiptObject(Receipt receipt) {
        this.receipts.add(receipt);
    }

}

