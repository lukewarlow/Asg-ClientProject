package com.nsa.team10.asgproject.validation;

public class ConflictException extends Exception
{
    public ConflictException(String type, String message)
    {
        super(type + ": " + message);
    }
}
