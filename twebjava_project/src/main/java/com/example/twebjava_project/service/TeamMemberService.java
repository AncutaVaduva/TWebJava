package com.example.twebjava_project.service;

import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.exception.TeamNotFoundException;
import com.example.twebjava_project.model.Team;
import com.example.twebjava_project.model.TeamMember;
import com.example.twebjava_project.repository.TeamMemberRepository;
import com.example.twebjava_project.repository.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamRepository teamRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository, TeamRepository teamRepository) {
        this.teamMemberRepository = teamMemberRepository;
        this.teamRepository = teamRepository;
    }

    public TeamDto add(String teamName, TeamMember teamMember){
        Optional<Team> teamOptional = teamRepository.findTeamByName(teamName);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            teamMemberRepository.add(teamMember,team);
            TeamDto teamDto = new TeamDto(team.getTeamName(), team.getCountryOfOrigin(), teamMemberRepository.getTeamMembersByTeamName(teamName));
            return teamDto;
        } else{
            throw new TeamNotFoundException(teamName);
        }
    }
}
