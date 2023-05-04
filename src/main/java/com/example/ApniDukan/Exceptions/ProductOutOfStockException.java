package com.example.ApniDukan.Exceptions;

public class ProductOutOfStockException extends Exception{
    public ProductOutOfStockException(){
        super("Sorry! product not available at this time");
    }
    public ProductOutOfStockException(String message){
        super(message);
    }
}
