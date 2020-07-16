package com.scanly.app.User;

import com.scanly.app.ShoppingList.ShoppingList;
import com.scanly.app.Receipt.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private ShoppingList shoppingList;
    private List<Receipt> receipts;

    public void addReceipt(String name, Date createdOn) {
        this.receipts.add(new Receipt(name,createdOn));
    }
    public void addReceiptObject(Receipt receipt) {
        this.receipts.add(receipt);
    }

}

