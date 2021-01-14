package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "Result", description = "Entire structure of a Result")
public class Result {

    private Stage stage;
    private Team team;
    private Double time;
    private long score;

}
