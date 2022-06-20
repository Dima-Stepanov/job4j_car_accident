package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Создание инцидента
     *
     * @param accident Accident.
     * @return Accident.
     */
    @Override
    public Accident create(Accident accident) {
        final String INSERT_SQL = "insert into accident (ac_name, ac_text, ac_address, at_id) values(?, ?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                con -> {
                    PreparedStatement ps = con.prepareStatement(INSERT_SQL, new String[]{"ac_id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder);
        accident.setId(keyHolder.getKey().intValue());
        return accident;
    }

    /**
     * Редактирование инцидента.
     *
     * @param id       int Accident.id
     * @param accident Accident
     * @return Accident
     */
    @Override
    public Accident edit(int id, Accident accident) {
        jdbc.update("update accident set ac_name = ?, ac_text = ?, ac_address = ?, "
                        + "at_id = ? where ac_id = ?", accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType().getId(), id);
        return accident;
    }

    /**
     * Поиск инцидента по ID
     *
     * @param id int Accident.id
     * @return Accident
     */
    @Override
    public Accident findById(int id) {
        return jdbc.queryForObject("select ac.ac_id, ac.ac_name, ac.ac_text, ac.ac_address,"
                        + "at.at_id, at.at_name from accident as ac "
                        + "inner join accident_type as at on ac.at_id = at.at_id "
                        + "where ac.ac_id = ?",
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("ac_id"));
                    accident.setName(rs.getString("ac_name"));
                    accident.setText(rs.getString("ac_text"));
                    accident.setAddress(rs.getString("ac_address"));
                    accident.setType(AccidentType.of(rs.getInt("at_id"), rs.getString("at_name")));
                    return accident;
                }, id);
    }

    /**
     * Поиск списка всех инцидентов.
     *
     * @return List<Accident>.
     */
    @Override
    public List<Accident> findAll() {
        return jdbc.query("select ac.ac_id, ac.ac_name, ac.ac_text, ac.ac_address,"
                        + "at.at_id, at.at_name from accident as ac "
                        + "inner join accident_type as at on ac.at_id = at.at_id",
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("ac_id"));
                    accident.setName(rs.getString("ac_name"));
                    accident.setText(rs.getString("ac_text"));
                    accident.setAddress(rs.getString("ac_address"));
                    accident.setType(AccidentType.of(rs.getInt("at_id"), rs.getString("at_name")));
                    return accident;
                });
    }
}
