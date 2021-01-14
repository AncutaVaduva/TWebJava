package com.example.twebjava_project.exception;

public class RallyNotFoundException extends RuntimeException{

    public RallyNotFoundException(String name) {
        super("Rally with name " + name + " doesn't exist ");
    }
}
