package com.example.twebjava_project.exception;

public class TeamMemberNotFoundException extends RuntimeException {

    public TeamMemberNotFoundException(String lastName, String firstName) {
        super("Team member with lastName " + lastName + " and firstName "+ firstName+ " doesn't exist ");
    }
}
