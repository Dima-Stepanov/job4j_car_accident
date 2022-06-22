package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 2. Spring Data [#296073]
 * AccidentTypeRepository класс управления базы данных через data jpa, модель AccidentType.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 22.06.2022
 */
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
