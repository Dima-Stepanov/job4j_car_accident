package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * ARJdbcTemplate класс для работы с базой данных таблица accident_rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.06.2022
 */
public class ARJdbcTemplate {
    private final JdbcTemplate jdbc;

    public ARJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Добавить данные в таблицу accident_rule.
     *
     * @param accidentId int
     * @param ruleId     int
     */
    public void addAccidentRule(int accidentId, int ruleId) {
        jdbc.update("insert into accident_rule(ac_id, r_id) values (?, ?)", accidentId, ruleId);
    }

    /**
     * Удаление всех ролей по ID инцидента.
     *
     * @param accidentId int.
     */
    public void deleteByAccidentId(int accidentId) {
        jdbc.update("delete from accident_rule where ac_id = ?", accidentId);
    }

    /**
     * Метод возвращает все роли по id инцидента.
     *
     * @param accidentId int.
     * @return Set.
     */
    public Set<Rule> findByAccidentId(int accidentId) {
        return new HashSet<>(jdbc.query("select ar.r_id, r.r_name from accident_rule as ar "
                        + "inner join rule as r "
                        + "on r.r_id = ar.r_id "
                        + "where ac_id = ?",
                (rs, rowNum) ->
                        Rule.of(rs.getInt("r_id"),
                                rs.getString("r_name")),
                accidentId));
    }
}
