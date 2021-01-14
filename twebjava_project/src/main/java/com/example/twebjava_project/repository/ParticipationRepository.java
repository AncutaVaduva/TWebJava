package com.example.twebjava_project.repository;

import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.model.Rally;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ParticipationRepository {

    private final JdbcTemplate jdbcTemplate;

    public ParticipationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add (long editionId, long teamId){
        String sql = "select edition_id, team_id from participations where edition_id = ? and team_id =?";
        RowMapper<Object> mapper = (resultSet, rowNum) ->
                new Object();

        List<Object> objects = jdbcTemplate.query(sql, mapper, editionId,teamId);
        if(objects != null && !objects.isEmpty()) {
            return 0;
        } else {
            String sqlInsert= "insert into participations values (null,?,?,curdate())";
            return jdbcTemplate.update(sqlInsert,editionId,teamId);
        }
    }

    public int delete (long editionId, long teamId){
        String sql = "delete from participations where edition_id=? and team_id=?";
        return jdbcTemplate.update(sql,editionId,teamId);
    }

    public boolean checkParticipationByStageAndTeam (long stageId, long teamId){
        String sql = "select * from stages s, participations p " +
                "where s.stage_id = ? and s.edition_id = p.edition_id " +
                "and team_id=?";
        RowMapper<Object> mapper = (resultSet, rowNum) ->
                new Object();

        List<Object> objects = jdbcTemplate.query(sql, mapper, stageId,teamId);
        if(objects != null && !objects.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    public List<TeamDto> getParticipationsByEditionId(int editionId){
        String sql = "select team_name, country_of_origin from teams t, participations p where edition_id=? and t.team_id=p.team_id";
        List<Map<String, Object>> teams = jdbcTemplate.queryForList(sql,editionId);
        List<TeamDto> teamDtos = new ArrayList<>();
        teams.forEach(item ->{
            teamDtos.add(new TeamDto((String) item.get("team_name"), (String) item.get("country_of_origin")));
        });
        return teamDtos;
    }

}
