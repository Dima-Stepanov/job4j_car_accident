package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * AccidentService слой бизнес логики.
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * 2. Spring Data [#296073]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Service
public class AccidentService {
    private final AccidentRepository accidents;

    public AccidentService(AccidentRepository accidents) {
        this.accidents = accidents;
    }

    /**
     * Добавление нового инцидента.
     *
     * @param accident Accident
     * @return Accident
     */
    public Accident create(Accident accident) {
        return accidents.save(accident);
    }

    /**
     * Поиск Accident по id
     *
     * @param id int
     * @return Optional.
     */
    public Optional<Accident> findById(Integer id) {
        return accidents.findById(id);
    }

    /**
     * Поиск всех инцидентов.
     * При этом происходит выборка ролей для каждого инцидента из таблицы accident_rule.
     *
     * @return List.
     */
    public Iterable<Accident> findAll() {
        return accidents.findAll();
    }
}
