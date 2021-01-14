package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@ApiModel(value = "Participation", description = "Entire structure of a Participation")
public class Participation {

    private Edition edition;
    private Team team;
    private LocalDate registrationDate;

}
