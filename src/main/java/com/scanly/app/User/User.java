package com.scanly.app.User;


public class User {


        private Long id;
        private String name;

        public User(){}

        public User(String name){

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

