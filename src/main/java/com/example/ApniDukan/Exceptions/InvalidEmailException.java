package com.example.ApniDukan.Exceptions;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(){
        super("email already exists! enter a valid email.");
    }
    public InvalidEmailException(String message){
        super(message);
    }
}
