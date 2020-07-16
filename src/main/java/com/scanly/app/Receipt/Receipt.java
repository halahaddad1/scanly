package com.scanly.app.Receipt;

import com.scanly.app.Product.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {


    private Long id;
    private String name;
    private Date createdOn;
    private List<Product> products;

    public Receipt(){}

    public Receipt(String name, Date createdOn){

        this.name = name;
        this.id = id;
        this.createdOn = createdOn;
        this.products = new ArrayList<Product>();

    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setCreatedOn(Date createdOn){
        this.createdOn = createdOn;
    }

    public Date getCreatedOn(){
        return createdOn;
    }

    public void addProducts(String name) {
        this.products.add(new Product(name));
    }
}
