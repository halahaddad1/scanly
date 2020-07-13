package com.scanly.app.Receipt;

public class Receipt {


    private Long id;
    private String name;

    public Receipt(){}

    public Receipt(String name){

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
