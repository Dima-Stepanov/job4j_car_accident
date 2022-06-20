package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.ARJdbcTemplate;
import ru.job4j.accident.repository.IStore;

import java.util.List;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * AccidentService слой бизнес логики.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Service
public class AccidentService {
    private final IStore<Accident> accidents;
    private final ARJdbcTemplate arJdbcTemplate;

    public AccidentService(IStore<Accident> accidents, ARJdbcTemplate arJdbcTemplate) {
        this.accidents = accidents;
        this.arJdbcTemplate = arJdbcTemplate;
    }

    /**
     * Добавление нового инцидента.
     *
     * @param accident Accident
     * @return Accident
     */
    public Accident create(Accident accident) {
        return accidents.create(accident);
    }

    /**
     * Редактировать Accident.
     *
     * @param id       int
     * @param accident Accident.
     * @return Accident.
     */
    public Accident edit(int id, Accident accident) {
        return accidents.edit(id, accident);
    }

    /**
     * Поиск Accident по id
     *
     * @param id int
     * @return Optional.
     */
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.findById(id));
    }

    /**
     * Поиск всех инцидентов.
     * При этом происходит выборка ролей для каждого инцидента из таблицы accident_rule.
     *
     * @return List.
     */
    public List<Accident> findAll() {
        List<Accident> accidentList = accidents.findAll();
        for (Accident accident : accidentList) {
            accident.setRules(arJdbcTemplate.findByAccidentId(accident.getId()));
        }
        return accidentList;
    }
}
