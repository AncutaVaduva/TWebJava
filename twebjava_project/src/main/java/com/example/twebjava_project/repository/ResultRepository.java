package com.example.twebjava_project.repository;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.ResultDto;
import com.example.twebjava_project.dto.TeamDto;
import com.example.twebjava_project.dto.TeamParticipationResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ResultRepository {

    private final JdbcTemplate jdbcTemplate;

    public ResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add (ResultDto resultDto){
        String sql = "select stage_id, team_id from results where stage_id=? and team_id=?";
        RowMapper<Object> mapper = (resultSet, rowNum) ->
                new Object();

        List<Object> objects = jdbcTemplate.query(sql, mapper, resultDto.getStageId(),resultDto.getTeamId());
        if(objects != null && !objects.isEmpty()) {
            return 0;
        } else {
            String sqlInsert= "insert into results values (null,?,?,?,?)";
            return jdbcTemplate.update(sqlInsert,resultDto.getStageId(),resultDto.getTeamId(),resultDto.getTime(),resultDto.getScore());
        }
    }

    public int updateScore(long participationId, int score){
        String sql = "update results set score = ? where result_id=?";
        return jdbcTemplate.update(sql,score,participationId);
    }

    public List<TeamParticipationResponse> getRankingForEdition(int editionId){
        String sql = "select team_name, country_of_origin, sum(score) as total_score\n" +
                "from teams t, results r, stages s\n" +
                "where s.edition_id = ?\n" +
                "and s.stage_id=r.stage_id\n" +
                "and t.team_id=r.team_id\n" +
                "group by team_name, country_of_origin\n" +
                "order by total_score desc;";
        List<Map<String, Object>> orderItems = jdbcTemplate.queryForList(sql,editionId);
        List<TeamParticipationResponse> teamDtos = new ArrayList<>();
        orderItems.forEach(item ->{
            teamDtos.add(new TeamParticipationResponse((String) item.get("team_name"),
                    (String) item.get("country_of_origin"),
                    (new BigDecimal(item.get("total_score").toString())).intValue()));
        });
        return teamDtos;
    }
}
