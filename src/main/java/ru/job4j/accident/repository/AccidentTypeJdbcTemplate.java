package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.06.2022
 */
public class AccidentTypeJdbcTemplate implements IStore<AccidentType> {
    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public AccidentType create(AccidentType accidentType) {
        jdbc.update("insert into accident_type (at_name) values (?);", accidentType.getName());
        return accidentType;
    }

    @Override
    public AccidentType edit(int id, AccidentType accidentType) {
        jdbc.update("update accident_type set at_name = ? where at_id = ?", accidentType.getName(), id);
        return accidentType;
    }

    @Override
    public AccidentType findById(int id) {
        return jdbc.queryForObject("select at_id, at_name from accident_type where at_id = ?",
                (rs, rowNum) ->
                        AccidentType.of(rs.getInt("at_id"),
                                rs.getString("at_name")),
                id);
    }

    @Override
    public List<AccidentType> findAll() {
        return jdbc.query("select at_id, at_name from accident_type",
                (rs, rowNum) ->
                        AccidentType.of(rs.getInt("at_id"),
                                rs.getString("at_name")));
    }
}
