package com.scanly.app.User;


import lombok.Data;

public class User {


        private Long id;
        private String name;

        User(){}

        User(String name){

            this.name = name;
        }
    }

