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
    private ShoppingList shoppingList;
    @Builder.Default
    private List<Receipt> receipts = new ArrayList<Receipt>();

    public void addReceipt(String name, Date createdOn) {
        this.receipts.add(new Receipt(name,createdOn));
    }
    public void addReceiptObject(Receipt receipt) {
        this.receipts.add(receipt);
    }

}

