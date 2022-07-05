package ru.job4j.accident.service;

import ru.job4j.accident.repository.jdbc.ARJdbcTemplate;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 0. Spring DataSource [#6878]
 * ARService класс для работы с базой данных таблица accident_rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 20.06.2022
 */
public class ARService {
    private final ARJdbcTemplate arJdbcTemplate;

    public ARService(ARJdbcTemplate arJdbcTemplate) {
        this.arJdbcTemplate = arJdbcTemplate;
    }

    /**
     * Метод сохраняет все роли привязанные к инциденту.
     *
     * @param accidentId Int
     * @param rules      String[]
     */
    public void saveAccidentRules(int accidentId, String[] rules) {
        for (String role : rules) {
            arJdbcTemplate.addAccidentRule(accidentId, Integer.parseInt(role));
        }
    }

    /**
     * Метод удаляет все роли и записывает новые по ID инцидента.
     *
     * @param accidentId Int
     * @param rules      String[]
     */
    public void updateAccidentRules(int accidentId, String[] rules) {
        arJdbcTemplate.deleteByAccidentId(accidentId);
        for (String role : rules) {
            arJdbcTemplate.addAccidentRule(accidentId, Integer.parseInt(role));
        }
    }
}
