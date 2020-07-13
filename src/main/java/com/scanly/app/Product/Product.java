package com.scanly.app.Product;

public class Product {

    private Long id;
    private String name;

    public Product(){}

    public Product(String name){

        this.name = name;
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
