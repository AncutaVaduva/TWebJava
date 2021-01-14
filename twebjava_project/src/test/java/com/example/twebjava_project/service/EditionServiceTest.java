package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.StageDto;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditionServiceTest {
    @Mock
    private EditionRepository editionRepository;
    @Mock
    private RallyRepository rallyRepository;
    @Mock
    private StageRepository stageRepository;
    @Mock
    private StageMapper stageMapper;
    @Mock
    private EditionMapper editionMapper;

    @InjectMocks
    private EditionService editionService;

    @Test
    @DisplayName("Add edition - happy flow")
    void addEditionTest() {
        Stage stage = new Stage ("s1",10.0,"asfalt");
        List<Stage> stages=  new ArrayList<>() {{add(stage);}};
        Edition edition = new Edition("ed1",stages);

        when(editionRepository.add(edition)).thenReturn((long) 1);

        editionService.add(edition);




        verify(stageRepository).add(stage);
    }

    @Test
    @DisplayName("Add edition - no stages")
    void addEditionNoStageTest() {
        Edition edition = new Edition("ed1",null);

        when(editionRepository.add(edition)).thenReturn((long) 1);

        editionService.add(edition);
    }

    @Test
    @DisplayName("Add stages to existing edition on a rally")
    void addEdition1Test() {
        Rally rally = new Rally();
        Edition edition = new Edition("e1");

        StageDto stage = new StageDto ("s1",2.1);
        List<StageDto> stages=  new ArrayList<>() {{add(stage);}};
        EditionDto edition1 = new EditionDto("r1","e1",stages);

        Stage s= new Stage("s1",2.1);

        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.of(rally));
        when(editionRepository.findEditionByRallyAndEditionName(any(),any())).thenReturn(Optional.of(edition));
        when(stageMapper.fromDto(any())).thenReturn(s);

        String result = editionService.add(edition1);

        assertEquals("New stages were added to Edition with editionName=e1 and rallyName=r1!",result);

        verify(stageRepository).add(s);
    }

    @Test
    @DisplayName("Add new edition with stages on rally")
    void addEdition2Test() {
        Rally rally = new Rally();
        Edition edition = new Edition("e1");

        StageDto stage = new StageDto ("s1",2.1);
        List<StageDto> stages=  new ArrayList<>() {{add(stage);}};
        EditionDto edition1 = new EditionDto("r1","e1",stages);

        Stage s= new Stage("s1",2.1);

        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.of(rally));
        when(editionRepository.findEditionByRallyAndEditionName(any(),any())).thenReturn(Optional.empty());
        when(editionMapper.fromDto(any())).thenReturn(edition);

        String result = editionService.add(edition1);

        assertEquals("New Edition with editionName=e1 was added on Rally with rallyName=r1!",result);
    }

    @Test
    @DisplayName("No rally found, generate exception")
    void addEdition3Test() {
        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.empty());

        RallyNotFoundException exception = assertThrows(RallyNotFoundException.class,
                () -> editionService.add(new EditionDto("r1","e1",null)));

        assertEquals("Rally with name r1 doesn't exist ",
                exception.getMessage());
    }

    @Test
    @DisplayName("Get edition by rally and edition name")
    void getEdition1Test(){
        Rally rally = new Rally();
        Edition edition = new Edition(1,"e1");

        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.of(rally));
        when(editionRepository.findEditionByRallyAndEditionName(any(),any())).thenReturn(Optional.of(edition));

        EditionDto editionDto = new EditionDto("r1","e1");
        EditionDto result = editionService.getEditionByRallyAndEditionName("r1","e1");

        assertEquals(editionDto.getEditionName(),result.getEditionName());
        assertEquals(editionDto.getEditionName(),result.getEditionName());
    }

    @Test
    @DisplayName("Get edition - No edition found")
    void getEdition2Test(){
        Rally rally = new Rally();

        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.of(rally));
        when(editionRepository.findEditionByRallyAndEditionName(any(),any())).thenReturn(Optional.empty());

        EditionNotFoundException exception = assertThrows(EditionNotFoundException.class,
                () -> editionService.getEditionByRallyAndEditionName("r1","e1"));

        assertEquals("Doesn't exist an edition with editionName=e1 and rallyName=r1",
                exception.getMessage());
    }

    @Test
    @DisplayName("Get edition - No rally found, generate exception")
    void getEdition3Test() {
        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.empty());

        RallyNotFoundException exception = assertThrows(RallyNotFoundException.class,
                () -> editionService.getEditionByRallyAndEditionName("r1","e1"));

        assertEquals("Rally with name r1 doesn't exist ",
                exception.getMessage());
    }

    @Test
    @DisplayName("Get edition by rally name happy")
    void getEditions1Test(){
        Rally rally = new Rally(1,"r1");
        EditionDto editionDto = new EditionDto("r1","e1");
        List<EditionDto> editions=  new ArrayList<>() {{add(editionDto);}};

        when(rallyRepository.findRallyByName(rally.getRallyName())).thenReturn(Optional.of(rally));
        when(editionRepository.getEditionsByRallyId(rally.getRallyId())).thenReturn(editions);

        List<EditionDto> ls= editionService.getEditionsByRallyName("r1");

        assertEquals(editionDto.getEditionName(),ls.get(0).getEditionName());
        assertEquals(editionDto.getRallyName(),ls.get(0).getRallyName());
    }

    @Test
    @DisplayName("Get editions - No rally found, generate exception")
    void  getEditions2Test() {
        when(rallyRepository.findRallyByName(any())).thenReturn(Optional.empty());

        RallyNotFoundException exception = assertThrows(RallyNotFoundException.class,
                () -> editionService.getEditionsByRallyName("r1"));

        assertEquals("Rally with name r1 doesn't exist ",
                exception.getMessage());
    }

    @Test
    @DisplayName("Get editions by edition name - happy flow")
    void  getEditions3Test() {
        EditionDto editionDto = new EditionDto(1, "r1","e1");
        List<EditionDto> editions=  new ArrayList<>() {{add(editionDto);}};

        when(editionRepository.getEditionsByEditionName("e1")).thenReturn(editions);

        List<EditionDto> ls = editionService.getEditionsByEditionName("e1");

        assertEquals(editionDto.getEditionName(),ls.get(0).getEditionName());
        verify(stageRepository).getStages(editionDto.getEditionId());
    }

    @Test
    @DisplayName("Get editions by edition name - empty list")
    void  getEditions4Test() {

        when(editionRepository.getEditionsByEditionName("e1")).thenReturn(new ArrayList<>());
        List<EditionDto> ls = editionService.getEditionsByEditionName("e1");
        assertEquals(0,ls.size());
    }

}
