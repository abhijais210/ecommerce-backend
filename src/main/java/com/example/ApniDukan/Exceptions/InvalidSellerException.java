package com.example.ApniDukan.Exceptions;

public class InvalidSellerException extends Exception{
    public InvalidSellerException(){
        super("seller Not Exists in DB!");
    }
    public InvalidSellerException(String message){
        super(message);
    }
}
