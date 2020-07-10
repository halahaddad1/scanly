package com.scanly.app.User;


import jdk.jfr.events.CertificateId;
import lombok.Data;


public class User {


    private Long id;
        private String name;

        User(){}

        User(String name){

            this.name = name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

    }

