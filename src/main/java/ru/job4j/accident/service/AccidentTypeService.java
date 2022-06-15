package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.IStore;

import java.util.List;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeService слой бизнес логики.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Service
public class AccidentTypeService {
    private final IStore<AccidentType> storeType;

    public AccidentTypeService(IStore<AccidentType> storeType) {
        this.storeType = storeType;
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(storeType.findById(id));
    }

    public List<AccidentType> findAll() {
        return storeType.findAll();
    }
}
