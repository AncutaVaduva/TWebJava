package com.example.twebjava_project.exception;

public class TeamNotFoundException extends  RuntimeException{

    public TeamNotFoundException(String name) {
        super("Team with name " + name + " doesn't exist ");
    }
}
