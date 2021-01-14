package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.exception.RallyNotFoundException;
import com.example.twebjava_project.exception.TeamNotFoundException;
import com.example.twebjava_project.exception.UpdateFailedException;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.repository.EditionRepository;
import com.example.twebjava_project.repository.RallyRepository;
import com.example.twebjava_project.repository.StageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class RallyService {

    private final RallyRepository rallyRepository;
    private final EditionRepository editionRepository;
    private final StageRepository stageRepository;
    private final EditionService editionService;

    public RallyService(RallyRepository rallyRepository, EditionRepository editionRepository, StageRepository stageRepository,
                        EditionService editionService) {
        this.rallyRepository = rallyRepository;
        this.editionRepository = editionRepository;
        this.stageRepository = stageRepository;
        this.editionService= editionService;
    }

    @Transactional
    public void add(Rally rallyParam){
        long rallyId = rallyRepository.add(rallyParam);

        if(rallyParam.getEditions()!= null && rallyParam.getEditions().size()!=0){
            Rally rally = new Rally();
            rally.setRallyId(rallyId);
            rallyParam.getEditions().forEach(editionParam->{
                editionParam.setRally(rally);

                editionService.add(editionParam);
            });

        }
    }

    public List<EditionDto> getRallyEditionsByRallyName( String rallyName){
        return editionService.getEditionsByRallyName(rallyName);
    }


    @Transactional
    public List<EditionDto> changeRallyName(String previousName, String changedName){
        Optional<Rally> rallyOptional = rallyRepository.findRallyByName(previousName);
        if (rallyOptional.isPresent()) {
            int result = rallyRepository.changeRallyName(previousName,changedName);
            if(result == 1){
                return editionService.getEditionsByRallyName(changedName);
            }
            else{
                throw new UpdateFailedException("Rally name could not be updated");
            }
        }
        else{
            throw new RallyNotFoundException(previousName);
        }
    }

    public void deleteRally(String rallyName){
        int result = rallyRepository.deleteRally(rallyName);
        if (result==0){
            throw new UpdateFailedException("Rally name doesn't exist or the rally can't be deleted");
        }
    }
}
