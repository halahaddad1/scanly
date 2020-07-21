package com.scanly.app.User;

import com.scanly.app.Product.Product;
import com.scanly.app.ProductRecommendations.implementSimpleSimilarityAlgorithm;
import com.scanly.app.Receipt.Receipt;
import com.scanly.app.ShoppingList.ShoppingList;
import com.scanly.app.ShoppingListProduct.ShoppingListProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ExecutionException;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    public class User {

        private String name;
        @Builder.Default
        private ShoppingList shoppingList = new ShoppingList("${name}'s shopping list");
        @Builder.Default
        private List<Receipt> receipts = new ArrayList<Receipt>();
        @Builder.Default
        private List<Product> ProductRecommendations = new ArrayList<Product>();

        public User(String name) {
            this.name = name;
           this.shoppingList = new ShoppingList("${name}'s shopping list");
           this.receipts = new ArrayList<Receipt>();
           this.ProductRecommendations = new ArrayList<Product>();
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

//        public void addReceipt(String name, Date createdOn) {
//            this.receipts.add(new Receipt(name,createdOn));
//        }
        public void addReceiptObject(Receipt receipt) {
            this.receipts.add(receipt);
        }
//        public void addProduct(Receipt receipt, String product, String name){
//        }

        public List<Receipt> setReceipts() {
            this.receipts = new ArrayList<Receipt>();
            return this.receipts;
        }

        public void setProductRecommendations() throws InterruptedException {

            implementSimpleSimilarityAlgorithm simple = new implementSimpleSimilarityAlgorithm();
            try {
                HashMap<String, String> recommendationsHash = simple.ShowRecommendations();
                String[] list = recommendationsHash.values().toArray(new String[0]);
                for(String item: list) {
                    for (Product shoppingItem : this.shoppingList.getShoppingItems()) {
                        if (!shoppingItem.getName().equals(item)) {
                            if (!this.ProductRecommendations.contains(shoppingItem)) {
                                this.ProductRecommendations.add(new Product(item));
                            }else{
                                continue;
                            }
                        }
                    }
                }
            } catch (ExecutionException e){
                 List<Product> ProductRecommendations = new ArrayList<Product>();
                this.ProductRecommendations = ProductRecommendations;
            }
        }

        public List<Product> getProductRecommendations() {
            return this.ProductRecommendations;
        }

    }


