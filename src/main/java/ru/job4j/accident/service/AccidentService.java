package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * AccidentService слой бизнес логики.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Service
public class AccidentService {
    private final AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public List<Accident> getAllAccident() {
        return accidentMem.getAll();
    }
}
