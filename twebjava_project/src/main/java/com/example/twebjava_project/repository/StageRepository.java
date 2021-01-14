package com.example.twebjava_project.repository;

import com.example.twebjava_project.dto.StageDto;
import com.example.twebjava_project.model.Rally;
import com.example.twebjava_project.model.Stage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class StageRepository {

    private final JdbcTemplate jdbcTemplate;

    public StageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long add(Stage stage){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "INSERT INTO stages VALUES(NULL, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, new String[] {"stage_id"});
            ps.setLong(1, stage.getEdition().getEditionId());
            ps.setString(2, stage.getStageName());
            ps.setDouble(3,stage.getDistance());
            ps.setString(4,stage.getType());
            ps.setTimestamp(5, Timestamp.valueOf(stage.getStartDate()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public List<StageDto> getStages(long editionId){
        String sql = "select * from stages where edition_id= ?";
        List<Map<String, Object>> stages = jdbcTemplate.queryForList(sql,editionId);
        List<StageDto> stageList = new ArrayList<>();
        stages.forEach(item ->{
            StageDto stage = new StageDto();
            stage.setStageId((int) item.get("stage_id"));
            stage.setStageName((String) item.get("stage_name"));
            stage.setDistance((Double) item.get("distance"));
            stage.setType((String) item.get("type"));
            stage.setStartDate(((Timestamp) item.get("start_date")).toLocalDateTime());
            stageList.add(stage);
        });
        return stageList;
    }

    public Optional findStageById(long stageId) {
        String sql = "select * from stages where stage_id = ?";

        RowMapper<Stage> mapper = (resultSet, rowNum) ->
                new Stage();

        List<Stage> stageList = jdbcTemplate.query(sql, mapper, stageId);
        if(stageList != null && !stageList.isEmpty()) {
            return Optional.of(stageList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
