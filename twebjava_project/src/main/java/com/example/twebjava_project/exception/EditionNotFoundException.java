package com.example.twebjava_project.exception;

public class EditionNotFoundException extends RuntimeException{
    public EditionNotFoundException(String rallyName, String editionName) {
        super("Doesn't exist an edition with editionName="+editionName+" and rallyName="+rallyName);
    }

    public EditionNotFoundException(int editionId) {
        super("Doesn't exist an edition with id "+ editionId);
    }
}
