package com.example.twebjava_project.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Stage", description = "Entire structure of a Stage")
public class Stage {

    private long stageId;
    @NotBlank(message = "Stage name cannot be null")
    private String stageName;
    @NotNull
    @Min(0)
    private Double distance;
    @NotNull
    private String type;
    @NotNull
    private LocalDateTime startDate;
    private @Valid Edition edition;

    public Stage(@NotBlank(message = "Stage name cannot be null") String stageName, @NotNull @Min(0) Double distance, @NotNull String type) {
        this.stageName = stageName;
        this.distance = distance;
        this.type = type;
        this.startDate = startDate;
    }

    public Stage(@NotBlank(message = "Stage name cannot be null") String stageName, @NotNull @Min(0) Double distance) {
        this.stageName = stageName;
        this.distance = distance;
    }
}
