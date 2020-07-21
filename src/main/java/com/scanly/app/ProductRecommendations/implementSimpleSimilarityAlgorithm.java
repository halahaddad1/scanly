package com.scanly.app.ProductRecommendations;

import com.scanly.app.ShoppingListProduct.ShoppingListProduct;
import com.scanly.app.service.FirebaseInitialize;
import com.scanly.app.service.FirebaseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class implementSimpleSimilarityAlgorithm {

    public static HashMap<String, String> ShowRecommendations() throws ExecutionException, InterruptedException {
            FirebaseInitialize initialize = new FirebaseInitialize();
            initialize.initialize();
            FirebaseService firebaseService = new FirebaseService();
            List<String> productNames = new ArrayList<String>();

            String[] userNames = new String[]{"cool Ross", "fake Ross", "sleepy faezeh", "who"};
            for (int i = 0; i < userNames.length; i++) {
                System.out.print(userNames[i]);
                List<ShoppingListProduct> shoppingListItems = firebaseService.getUserDetails(userNames[i]).getShoppingList().getShoppingItems();
                for (ShoppingListProduct product : shoppingListItems) {
                    if (!productNames.contains(product.getName())) {
                        productNames.add(product.getName());
                    } else {
                        continue;
                    }
                }
            }
            Collections.sort(productNames);
            System.out.println(productNames);
            int[][] matrix = new int[userNames.length][productNames.size()];

//     let's loop through array to populate the matrix
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    ShoppingListProduct item = firebaseService.getShoppingListProductDetails(userNames[row], productNames.get(col));
                    if (item == null) {
                        matrix[row][col] = 0;
                    } else {
                        matrix[row][col] = item.getTimesBought();
                    }
                }
            }

            SimpleSimilarityAlgorithm algo = new SimpleSimilarityAlgorithm(matrix);
            double[][] imalgo = algo.calculateSimilarity();
            System.out.println(imalgo);


            // let's loop through array to print each row and column
            HashMap<String,String> ProductRecommendations = new HashMap<String, String>();
            for (int row = 0; row < imalgo.length; row++) {
//        for (int col = 0; col < imalgo[row].length-1; col++) {
                List<Double> nonZero = new ArrayList<Double>();
                for (int col2 = 0; col2 < imalgo[row].length; col2++) {
                    if (imalgo[row][col2] > 0) {
                        nonZero.add(imalgo[row][col2]);
                    }
                }
                Collections.sort(nonZero);
                if (nonZero.size() > 0) {
                    double product = nonZero.get(0);

                    for (int i = 0; i < imalgo.length; i++) {
                        if (imalgo[row][i] == product) {
                            ProductRecommendations.put(productNames.get(row), productNames.get(i));
                        } else {
                            continue;
                        }
                    }
                }
            }
        return ProductRecommendations;
    }
}



