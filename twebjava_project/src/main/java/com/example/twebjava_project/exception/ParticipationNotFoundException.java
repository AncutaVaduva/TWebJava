package com.example.twebjava_project.exception;

public class ParticipationNotFoundException extends RuntimeException {

    public ParticipationNotFoundException(String teamName, String rallyName, String editionName) {
        super("Team with name "+ teamName + " is not registered at edition with editionName "+editionName+" and rallyName "+rallyName);
    }

    public ParticipationNotFoundException(long stageId, String teamName) {
        super("Team with name "+ teamName + " is not registered at edition which have a stage with stageId "+stageId);
    }
}
