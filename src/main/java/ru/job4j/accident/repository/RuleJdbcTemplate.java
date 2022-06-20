package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * RuleJdbcTemplate класс для работы с базой данных таблица rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.06.2022
 */
@Repository
public class RuleJdbcTemplate implements IStore<Rule> {
    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Добавить Role.
     *
     * @param rule Role.
     * @return Role.
     */
    @Override
    public Rule create(Rule rule) {
        jdbc.update("insert into rule(r_name) values (?)", rule.getName());
        return rule;
    }

    /**
     * Редактировать Rule по id.
     *
     * @param id   int.
     * @param rule Role
     * @return Role.
     */
    @Override
    public Rule edit(int id, Rule rule) {
        jdbc.update("update rule set r_name = ? where r_id = ?", rule.getName(), id);
        return rule;
    }

    /**
     * Поиск Rule по ID.
     *
     * @param id int.
     * @return Rule.
     */
    @Override
    public Rule findById(int id) {
        return jdbc.queryForObject("select r_id, r_name from rule where r_id = ?",
                (rs, rowNum) ->
                        Rule.of(rs.getInt("r_id"),
                                rs.getString("r_name")), id);
    }

    /**
     * Метод вернет все роли из таблицы rule.
     *
     * @return List
     */
    @Override
    public List<Rule> findAll() {
        return jdbc.query("select r_id, r_name from rule",
                (rs, rowNum) ->
                        Rule.of(rs.getInt("r_id"),
                                rs.getString("r_name")));
    }
}
