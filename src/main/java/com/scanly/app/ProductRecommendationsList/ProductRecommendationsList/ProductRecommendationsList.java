package com.scanly.app.ProductRecommendationsList.ProductRecommendationsList;

import com.scanly.app.Product.Product;
import com.scanly.app.ShoppingListProduct.ShoppingListProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRecommendationsList {


        private String name ;
    @Builder.Default
    private List<Product> Recommendations= new ArrayList<Product>();


        public void addShoppingItems(ShoppingListProduct product) {
            this.Recommendations.add(product);
        }

    }

