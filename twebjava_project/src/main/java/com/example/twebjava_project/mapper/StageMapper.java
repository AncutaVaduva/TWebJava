package com.example.twebjava_project.mapper;

import com.example.twebjava_project.dto.StageDto;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageMapper {

    public Stage fromDto(StageDto stageDto){
        Stage stage = new Stage();
        stage.setStageName(stageDto.getStageName());
        stage.setDistance(stageDto.getDistance());
        stage.setType(stageDto.getType());
        stage.setStartDate(stageDto.getStartDate());
        Edition edition = new Edition();
        stage.setEdition(edition);
        return stage;
    }
}
