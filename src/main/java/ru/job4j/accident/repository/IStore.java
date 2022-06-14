package ru.job4j.accident.repository;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * IStore интерфейс описывает поведения слоя Repository (хранилища).
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
public interface IStore<T> {
    T create(T type);

    T edit(int id, T type);

    T findById(int id);

    List<T> findAll();
}
