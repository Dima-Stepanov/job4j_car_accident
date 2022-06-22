package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * IStore интерфейс описывает поведения слоя Repository (хранилища).
 * 1. Spring ORM [#2093]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
public interface IStore<T> {
    T create(T type);

    T edit(int id, T type);

    T findById(int id);

    List<T> findAll();

    /**
     * Шаблон WRAPPER
     *
     * @param command Function
     * @param sf      SessionFactory
     * @param <T> Type
     * @return T Type
     */
    default <T> T tx(final Function<Session, T> command, final SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try (session) {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
