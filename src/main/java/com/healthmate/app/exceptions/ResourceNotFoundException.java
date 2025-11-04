package com.healthmate.app.exceptions;

public class ResourceNotFoundException extends RuntimeException 
{
    public ResourceNotFoundException(String message) 
    {
        super(message);
    }  
}
