package com.example.twebjava_project.dto;

import com.example.twebjava_project.model.Stage;
import com.example.twebjava_project.model.Team;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ApiModel(value = "Result Dto", description = "Required details needed to create a new Result")
public class ResultDto {

    @NotBlank(message = "Team name cannot be null")
    private String teamName;
    private int teamId;
    @NotNull
    private int stageId;
    @NotNull
    private Double time;
    @NotNull
    private long score;
}
