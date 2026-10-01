package com.codingshuttle.SecurityApp.SecurityApplication.Exceptions;


public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }
}
