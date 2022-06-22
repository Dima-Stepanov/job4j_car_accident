package ru.job4j.accident.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.IStore;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeService слой бизнес логики.
 * 2. Spring Data [#296073]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Service
public class AccidentTypeService {
    private final AccidentTypeRepository storeType;

    public AccidentTypeService(AccidentTypeRepository storeType) {
        this.storeType = storeType;
    }

    /**
     * Поиск AccidentType по ID.
     *
     * @param id int
     * @return Optional.
     */
    public Optional<AccidentType> findById(int id) {
        return storeType.findById(id);
    }

    /**
     * Вернуть все типы инцидентов.
     *
     * @return List
     */
    public Iterable<AccidentType> findAll() {
        return storeType.findAll();
    }
}
