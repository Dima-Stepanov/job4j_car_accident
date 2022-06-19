package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * AccidentJdbcTemplate класс для работы с базой данных.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 19.06.2022
 */
@Repository
public class AccidentJdbcTemplate implements IStore<Accident> {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident create(Accident accident) {
        jdbc.update("insert into accident(ac_name, ac_text, ac_address, at_id) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId());
        return accident;
    }

    @Override
    public Accident edit(int id, Accident accident) {
        jdbc.update("update accident set ac_name = ?, ac_text = ?, ac_address = ?, "
                        + "at_id = ? where ac_id = ?", accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(), id);
        return accident;
    }

    @Override
    public Accident findById(int id) {
        return jdbc.queryForObject("select ac.ac_id, ac.ac_name, ac.ac_text, ac.ac_address,"
                        + "at.at_id, at.at_name from accident as ac "
                        + "inner join accident_type as at on ac.at_id = at.at_id "
                        + "where ac.ac_id = ?",
                (rs, rowNum) -> Accident.of(
                        rs.getInt("ac_id"),
                        rs.getString("ac_name"),
                        rs.getString("ac_text"),
                        rs.getString("ac_address"),
                        AccidentType.of(rs.getInt("at_id"), rs.getString("at_name")),
                        Set.of(Rule.of(1, "Статья 1"))),
                id);
    }

    @Override
    public List<Accident> findAll() {
        return jdbc.query("select ac.ac_id, ac.ac_name, ac.ac_text, ac.ac_address,"
                        + "at.at_id, at.at_name from accident as ac "
                        + "inner join accident_type as at on ac.at_id = at.at_id",
                (rs, rowNum) ->
                        Accident.of(
                                rs.getInt("ac_id"),
                                rs.getString("ac_name"),
                                rs.getString("ac_text"),
                                rs.getString("ac_address"),
                                AccidentType.of(rs.getInt("at_id"), rs.getString("at_name")),
                                Set.of(Rule.of(1, "Статья 1"))));
    }
}
