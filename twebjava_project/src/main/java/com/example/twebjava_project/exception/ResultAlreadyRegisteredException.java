package com.example.twebjava_project.exception;

public class ResultAlreadyRegisteredException extends RuntimeException{

    public ResultAlreadyRegisteredException(String teamName) {
        super("The result for team "+ teamName+" on this stage is already registered");
    }
}
