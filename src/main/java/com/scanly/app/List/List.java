package com.scanly.app.List;

public class List {


    private Long id;
    private String name;

    public List(){}

    public List(String name){

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


