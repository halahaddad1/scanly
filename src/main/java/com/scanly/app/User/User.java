package com.scanly.app.User;

import jdk.jfr.DataAmount;
import jdk.jfr.events.CertificateId;
import lombok.Data;

public class User {

    @Data
    class User{

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String name;

        User(){}

        User(String name){

            this.name = name;
        }
    }
}
