package com.example.twebjava_project.exception;

public class StageNotFoundException extends RuntimeException{

    public StageNotFoundException() {
        super("Stage doesn't exist");
    }
}
