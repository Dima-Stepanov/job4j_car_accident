package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * UserRepository класс управления базы данных через data jpa, модель User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
