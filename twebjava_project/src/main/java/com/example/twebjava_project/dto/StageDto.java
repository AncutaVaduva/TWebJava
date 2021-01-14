package com.example.twebjava_project.dto;

import com.example.twebjava_project.model.Edition;
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
@ApiModel(value = "Stage Dto", description = "Required details needed to create a new Stage")
public class StageDto {
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

    public StageDto(@NotBlank(message = "Stage name cannot be null") String stageName, @NotNull @Min(0) Double distance) {
        this.stageName = stageName;
        this.distance = distance;
    }
}
