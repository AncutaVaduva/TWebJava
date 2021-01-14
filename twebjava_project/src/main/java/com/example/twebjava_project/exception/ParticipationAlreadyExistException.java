package com.example.twebjava_project.exception;

public class ParticipationAlreadyExistException extends RuntimeException {
    public ParticipationAlreadyExistException(String teamName, String rallyName, String editionName) {
        super("Team with name "+ teamName + " is already registered at edition with editionName "+editionName+" and rallyName "+rallyName);
    }
}
