package com.example.ApniDukan.Exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(){
        super("Product not Exists in DB!");
    }
    public ProductNotFoundException(String message){
        super(message);
    }
}
