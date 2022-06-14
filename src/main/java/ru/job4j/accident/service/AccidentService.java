package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.IStore;

import java.util.List;

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

    public AccidentService(IStore<Accident> accidents) {
        this.accidents = accidents;
    }

    public Accident create(Accident accident) {
        return accidents.create(accident);
    }

    public Accident edit(int id, Accident accident) {
        return accidents.edit(id, accident);
    }

    public Accident findById(int id) {
        return accidents.findById(id);
    }

    public List<Accident> findAll() {
        return accidents.findAll();
    }


}
