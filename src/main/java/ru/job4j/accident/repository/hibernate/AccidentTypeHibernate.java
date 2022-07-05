package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.IStore;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * AccidentTypeHibernate класс для работы с базой данны через Hibernate,
 * модель AccidentType.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 22.06.2022
 */
public class AccidentTypeHibernate implements IStore<AccidentType> {
    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public AccidentType create(AccidentType accidentType) {
        return tx(session -> {
            session.save(accidentType);
            return accidentType;
        }, sf);
    }

    @Override
    public AccidentType edit(int id, AccidentType accidentType) {
        return tx(session -> {
            accidentType.setId(id);
            session.update(accidentType);
            return accidentType;
        }, sf);
    }

    @Override
    public AccidentType findById(int id) {
        return tx(session -> session
                        .get(AccidentType.class, id),
                sf);
    }

    @Override
    public List<AccidentType> findAll() {
        return tx(session -> session
                        .createQuery("from AccidentType order by id").list(),
                sf);
    }
}
