package com.example.twebjava_project.repository;

import com.example.twebjava_project.dto.EditionDto;
import com.example.twebjava_project.dto.StageDto;
import com.example.twebjava_project.model.Edition;
import com.example.twebjava_project.model.Rally;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class EditionRepository {

    private final JdbcTemplate jdbcTemplate;

    public EditionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long add(Edition edition){

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO editions VALUES(NULL, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {"edition_id"});
            ps.setLong(1, edition.getRally().getRallyId());
            ps.setString(2, edition.getEditionName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional findEditionByRallyAndEditionName(String rallyName, String editionName){

        String sql = "select edition_id from rallies r, editions e where r.rally_id=e.rally_id and rally_name = ? and edition_name=?";

        RowMapper<Edition> mapper = (resultSet, rowNum) ->
                new Edition(resultSet.getLong("edition_id"));
        List<Edition> editions = jdbcTemplate.query(sql, mapper, rallyName,editionName);

        if(editions != null && !editions.isEmpty()) {
            return Optional.of(editions.get(0));
        } else {
            return Optional.empty();
        }
    }

    public Optional findEditionByEditionId(int editionId){

        String sql = "select * from editions where edition_id=?";
        RowMapper<Edition> mapper = (resultSet, rowNum) ->
                new Edition(resultSet.getLong("edition_id"));
        List<Edition> editions = jdbcTemplate.query(sql, mapper, editionId);

        if(editions != null && !editions.isEmpty()) {
            return Optional.of(editions.get(0));
        } else {
            return Optional.empty();
        }
    }

    public List<EditionDto> mapQueryResultToObject(List<Map<String, Object>> editions){

        List<EditionDto> editionDtos = new ArrayList<>();
        editions.forEach(item ->{
            EditionDto editionDto = new EditionDto();
            editionDto.setRallyId((int) item.get("rally_id"));
            editionDto.setRallyName((String) item.get("rally_name"));
            editionDto.setEditionId((int) item.get("edition_id"));
            editionDto.setEditionName((String) item.get("edition_name"));
            editionDtos.add(editionDto);
        });
        return editionDtos;

    }

    public List<EditionDto> getEditionsByRallyId(long rallyId){

        String sql = "select * from editions e, rallies r where r.rally_id= ? and e.rally_id=r.rally_id";
        List<Map<String, Object>> editions = jdbcTemplate.queryForList(sql,rallyId);
        return mapQueryResultToObject(editions);

    }

    public List<EditionDto> getEditionsByEditionName(String editionName){

        String sql = "select * from editions e, rallies r where edition_name=? and e.rally_id=r.rally_id";
        List<Map<String, Object>> editions = jdbcTemplate.queryForList(sql,editionName);
        return mapQueryResultToObject(editions);
    }

    public List<EditionDto> getEditionsByTeamNameParticipations(String teamName){

        String sql = "select * from rallies r, editions e, participations p, teams t where r.rally_id=e.rally_id and e.edition_id=p.edition_id and p.team_id=t.team_id and team_name=?";
        List<Map<String, Object>> editions = jdbcTemplate.queryForList(sql,teamName);
        return mapQueryResultToObject(editions);
    }

}
