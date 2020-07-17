package com.scanly.app.Receipt;

import com.scanly.app.Product.Product;
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
public class Receipt  {

    private String name;
    private Date createdOn;
    @Builder.Default
    private List<Product> products = new ArrayList<Product>(); ;

    public Receipt(String name, Date createdOn) {
        this.name = name;
        this.createdOn = createdOn;
        this.products = new ArrayList<Product>();
    }

    public void addProducts(String name) {
        this.products.add(new Product(name));
    }

    public void addProductObject(Product product) {
        this.products.add(product);
    }

}
