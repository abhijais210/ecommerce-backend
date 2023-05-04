package com.example.ApniDukan.Exceptions;

public class ProductAlreadyRegisteredException extends Exception{
    public ProductAlreadyRegisteredException(){
        super("This seller has already registered with this product!, just update the product");
    }
    public ProductAlreadyRegisteredException(String message){
        super(message);
    }
}
