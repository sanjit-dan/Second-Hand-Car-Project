package com.example.demo.exception;

public class AllReadyExistsException extends RuntimeException{

    public AllReadyExistsException(String message){
        super(message);
    }
}
