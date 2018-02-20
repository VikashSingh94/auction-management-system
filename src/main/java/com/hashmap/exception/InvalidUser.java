package com.hashmap.exception;

public class InvalidUser extends RuntimeException {
    public InvalidUser(String s){
        super(s);
    }
}
