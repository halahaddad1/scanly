package com.scanly.app.User;

import com.scanly.app.Product.Product;
import com.scanly.app.ProductRecommendations.implementSimpleSimilarityAlgorithm;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.ShoppingList.ShoppingList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    private String name;
    private ShoppingList shoppingList;
    @Builder.Default
    private List<Receipt> receipts = new ArrayList<Receipt>();
    private List<Product> ProductRecommendations = new ArrayList<Product>();

    public User(String user) {
        this.name = user;
        this.shoppingList = new ShoppingList(user);
        this.receipts = new ArrayList<Receipt>();
        this.ProductRecommendations= new ArrayList<Product>();
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

    public List<Product> setProductRecommendations() throws ExecutionException, InterruptedException {
        implementSimpleSimilarityAlgorithm simple = new implementSimpleSimilarityAlgorithm();
        HashMap<String,String> recommendationsHash = implementSimpleSimilarityAlgorithm.ShowRecommendations();
        String[] list = (String[]) recommendationsHash.values().toArray();
        for(String item: list){
            for(Product shoppingItems: this.shoppingList.getShoppingItems()){
                if (!shoppingItems.getName().equals(item)){
                    this.ProductRecommendations.add(new Product(item));
                }
            }
        }
        return this.ProductRecommendations;
    }

    public List<Product> getProductRecommendations() throws ExecutionException, InterruptedException {
     return this.setProductRecommendations();
    }

}

