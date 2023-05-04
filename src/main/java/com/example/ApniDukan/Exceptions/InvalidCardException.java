package com.example.ApniDukan.Exceptions;

public class InvalidCardException extends Exception{
    public InvalidCardException(String message){
        super(message);
    }
    public InvalidCardException(){
        super("Invalid card details!");
    }
}
