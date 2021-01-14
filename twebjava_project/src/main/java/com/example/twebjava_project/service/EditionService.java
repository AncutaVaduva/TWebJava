package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.exception.EditionNotFoundException;
import com.example.twebjava_project.exception.RallyNotFoundException;
import com.example.twebjava_project.mapper.EditionMapper;
import com.example.twebjava_project.mapper.StageMapper;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Stage;
import com.example.twebjava_project.repository.EditionRepository;
import com.example.twebjava_project.repository.RallyRepository;
import com.example.twebjava_project.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditionService {

    private final EditionRepository editionRepository;
    public final RallyRepository rallyRepository;
    private final StageRepository stageRepository;
    private final EditionMapper editionMapper;
    private final StageMapper stageMapper;


    public EditionService(EditionRepository editionRepository, RallyRepository rallyRepository, StageRepository stageRepository, EditionMapper editionMapper, StageMapper stageMapper) {
        this.editionRepository = editionRepository;
        this.rallyRepository = rallyRepository;
        this.stageRepository = stageRepository;
        this.editionMapper = editionMapper;
        this.stageMapper = stageMapper;
    }

    public void add(Edition editionParam){

        long editionId=editionRepository.add(editionParam);
        if(editionParam.getStages() != null && editionParam.getStages().size()!=0){
            Edition edition = new Edition();
            edition.setEditionId(editionId);
            editionParam.getStages().forEach(stage ->{
                stage.setEdition(edition);
                stageRepository.add(stage);
            });
        }
    }

    public String add(EditionDto editionDto) {

        Optional<Rally> rallyOptional = rallyRepository.findRallyByName(editionDto.getRallyName());
        if (rallyOptional.isPresent()) {

            Optional<Edition> editionOptional = editionRepository.findEditionByRallyAndEditionName(editionDto.getRallyName(),editionDto.getEditionName());
            if (editionOptional.isPresent()) {
                editionDto.getStages().forEach(stageDto -> {
                    Stage stage = stageMapper.fromDto(stageDto);
                    stage.setEdition(editionOptional.get());
                    stageRepository.add(stage);
                });
                return "New stages were added to Edition with editionName="+editionDto.getEditionName()+" and rallyName="+editionDto.getRallyName()+"!";
            } else {
                editionDto.setRallyId(rallyOptional.get().getRallyId());
                Edition edition = editionMapper.fromDto(editionDto);
                add(edition);
                return "New Edition with editionName="+editionDto.getEditionName()+" was added on Rally with rallyName="+editionDto.getRallyName()+"!";
            }

        } else {
            throw new RallyNotFoundException(editionDto.getRallyName());
        }
    }

    public EditionDto getEditionByRallyAndEditionName(String rallyName, String editionName){
        Optional<Rally> rallyOptional = rallyRepository.findRallyByName(rallyName);
        if (rallyOptional.isPresent()) {
            Optional<Edition> editionOptional = editionRepository.findEditionByRallyAndEditionName(rallyName,editionName);
            if (editionOptional.isPresent()) {
                EditionDto editionDto = new EditionDto();
                editionDto.setRallyId(rallyOptional.get().getRallyId());
                editionDto.setRallyName(rallyName);
                editionDto.setEditionId(editionOptional.get().getEditionId());
                editionDto.setEditionName(editionName);
                editionDto.setStages(stageRepository.getStages(editionOptional.get().getEditionId()));

                return editionDto;
            } else{
                throw new EditionNotFoundException(rallyName,editionName);
            }
        } else {
            throw new RallyNotFoundException(rallyName);
        }
    }

    public List<EditionDto> getEditionsByRallyName (String rallyName){
        Optional<Rally> rallyOptional = rallyRepository.findRallyByName(rallyName);
        if (rallyOptional.isPresent()) {
            List<EditionDto> editionsByRallyId = editionRepository.getEditionsByRallyId(rallyOptional.get().getRallyId());
            editionsByRallyId.forEach(editionDto -> {
                editionDto.setStages(stageRepository.getStages(editionDto.getEditionId()));
            });
            return  editionsByRallyId;
        } else {
            throw new RallyNotFoundException(rallyName);
        }
    }

    public List<EditionDto> getEditionsByEditionName(String editionName){
        List<EditionDto> editionsByRallyId = editionRepository.getEditionsByEditionName(editionName);
        editionsByRallyId.forEach(editionDto -> {
            editionDto.setStages(stageRepository.getStages(editionDto.getEditionId()));
        });
        return  editionsByRallyId;
    }
}
